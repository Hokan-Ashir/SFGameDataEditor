package sfgamedataeditor.mvc;

import sfgamedataeditor.events.processing.EventProcessor;
import sfgamedataeditor.events.types.Event;
import sfgamedataeditor.events.types.ShowViewEvent;
import sfgamedataeditor.events.types.UnShowViewEvent;
import sfgamedataeditor.events.types.UpdateViewModelEvent;
import sfgamedataeditor.mvc.objects.Model;
import sfgamedataeditor.mvc.viewhierarchy.ViewHierarchy;
import sfgamedataeditor.views.common.ControllableView;
import sfgamedataeditor.views.main.modules.common.eventhistory.EventHistory;
import sfgamedataeditor.views.main.modules.common.eventhistory.EventHistoryModel;
import sfgamedataeditor.views.main.modules.common.eventhistory.EventHistoryModelParameter;
import sfgamedataeditor.views.main.modules.common.eventhistory.EventHistoryView;

import java.util.*;

public enum  ShowViewDispatcher {
    INSTANCE;

    private List<Class<? extends ControllableView>> viewsOnTheScreen = new ArrayList<>();
    private Map<ClassTuple<? extends ControllableView, ? extends ControllableView>, AbstractModelCreator> modelCreatorsMap = new HashMap<>();

    ShowViewDispatcher() {
//        modelCreatorsMap.put()
    }

    public void showView(Class<? extends ControllableView> viewClassToShow, Model model) {
        List<Event> eventsToProcess = new ArrayList<>();
        boolean viewExistsOnScreen = isViewExistsOnScreen(viewClassToShow);
        if (viewExistsOnScreen) {
            UpdateViewModelEvent updateViewModelEvent = new UpdateViewModelEvent(viewClassToShow, model);
            eventsToProcess.add(updateViewModelEvent);
        } else {
            List<Class<? extends ControllableView>> viewsToShow = ViewHierarchy.INSTANCE.getViewsToShow(viewClassToShow);
            List<Event> toProcess = createEventsToProcess(viewsToShow, model);
            eventsToProcess.addAll(toProcess);
            List<Event> unrenderEvents = Collections.emptyList();//createUnRenderViewEventList(viewsToShow);
            eventsToProcess.addAll(unrenderEvents);
        }

        for (Event event : eventsToProcess) {
            EventHistory.INSTANCE.addEventToHistory(event);
            EventProcessor.INSTANCE.process(event);
        }

        updateEventHistoryButtonsState();
    }

    private List<Event> createEventsToProcess(List<Class<? extends ControllableView>> viewBranch, Model model) {
        List<Event> events = new ArrayList<>();
        Model parentModel = model;
        for (int i = 0; i < viewBranch.size(); ++i) {
            if (i + 1 == viewBranch.size()) {
                break;
            }

            Class<? extends ControllableView> viewI = viewBranch.get(i);
            Class<? extends ControllableView> viewI1 = viewBranch.get(i + 1);
            boolean existsOnScreen = isViewExistsOnScreen(viewI);
            if (existsOnScreen) {
                AbstractModelCreator abstractModelCreator = modelCreatorsMap.get(new ClassTuple<>(viewI, viewI1));
                if (abstractModelCreator != null) {
                    parentModel = abstractModelCreator.createModel(parentModel);
                    UpdateViewModelEvent updateViewModelEvent = new UpdateViewModelEvent(viewI, parentModel);
                    events.add(updateViewModelEvent);
                }
            } else {
                ShowViewEvent showViewEvent = new ShowViewEvent(viewI, parentModel);
                events.add(showViewEvent);
            }
        }

        if (events.size() == 0 && viewBranch.size() != 0) {
            for (Class<? extends ControllableView> aClass : viewBranch) {
                ShowViewEvent event = new ShowViewEvent(aClass, null);
                events.add(event);
            }
        }

        return events;
    }

    private List<Event> createUnRenderViewEventList(List<Class<? extends ControllableView>> viewsToShow) {
        if (viewsOnTheScreen.isEmpty()) {
            return Collections.emptyList();
        }

        List<Event> unrenderEvents = new ArrayList<>();
        for (int i = 0; i < viewsToShow.size(); i++) {
            if (viewsToShow.get(i).equals(viewsOnTheScreen.get(i))) {
                continue;
            }

            for (int j = i; j < viewsOnTheScreen.size(); j++) {
                UnShowViewEvent event = new UnShowViewEvent(viewsOnTheScreen.get(j));
                unrenderEvents.add(event);
            }
            return unrenderEvents;
        }

        return unrenderEvents;
    }

    private boolean isViewExistsOnScreen(Class<? extends ControllableView> view) {
        return viewsOnTheScreen.contains(view);
    }

    private void updateEventHistoryButtonsState() {
        boolean isRedoPossible = EventHistory.INSTANCE.isRedoPossible();
        boolean isUndoPossible = EventHistory.INSTANCE.isUndoPossible();
        EventHistoryModelParameter parameter = new EventHistoryModelParameter(isRedoPossible, isUndoPossible);
        EventHistoryModel model = new EventHistoryModel(parameter);
        UpdateViewModelEvent event = new UpdateViewModelEvent(EventHistoryView.class, model);
        EventProcessor.INSTANCE.process(event);
    }

    public void registerRenderedView(Class<? extends ControllableView> classViewToRegister) {
        viewsOnTheScreen.add(classViewToRegister);
    }

    public void unregisterUnRenderedView(Class<? extends ControllableView> classViewToUnRegister) {
        viewsOnTheScreen.remove(classViewToUnRegister);
    }
}
