package sfgamedataeditor.mvc;

import sfgamedataeditor.events.processing.EventProcessor;
import sfgamedataeditor.events.types.Event;
import sfgamedataeditor.events.types.ShowViewEvent;
import sfgamedataeditor.events.types.UnShowViewEvent;
import sfgamedataeditor.events.types.UpdateViewModelEvent;
import sfgamedataeditor.mvc.objects.Model;
import sfgamedataeditor.mvc.viewhierarchy.ViewHierarchy;
import sfgamedataeditor.views.common.RenderableView;
import sfgamedataeditor.views.main.modules.common.eventhistory.EventHistory;
import sfgamedataeditor.views.main.modules.common.eventhistory.EventHistoryModel;
import sfgamedataeditor.views.main.modules.common.eventhistory.EventHistoryModelParameter;
import sfgamedataeditor.views.main.modules.common.eventhistory.EventHistoryView;

import java.util.*;

public enum  ShowViewDispatcher {
    INSTANCE;

    private List<Class<? extends RenderableView>> viewsOnTheScreen = new ArrayList<>();
    private Map<ClassTuple<? extends RenderableView, ? extends RenderableView>, AbstractModelCreator> modelCreatorsMap = new HashMap<>();

    ShowViewDispatcher() {
//        modelCreatorsMap.put()
    }

    public void showView(Class<? extends RenderableView> viewClassToShow, Model model) {
        boolean viewExistsOnScreen = isViewExistsOnScreen(viewClassToShow);
        if (viewExistsOnScreen) {
            UpdateViewModelEvent updateViewModelEvent = new UpdateViewModelEvent(viewClassToShow, model);
            EventHistory.INSTANCE.addEventToHistory(updateViewModelEvent);
            EventProcessor.INSTANCE.process(updateViewModelEvent);
            updateEventHistoryButtonsState();
        } else {
            List<Class<? extends RenderableView>> viewsToShow = ViewHierarchy.INSTANCE.getViewsToShow(viewClassToShow);
            List<Event> eventsToProcess = createEventsToProcess(viewsToShow, model);
            List<Event> unrenderEvents = Collections.emptyList();//createUnRenderViewEventList(viewsToShow);
            eventsToProcess.addAll(unrenderEvents);
            for (Event eventsToProces : eventsToProcess) {
                EventProcessor.INSTANCE.process(eventsToProces);
            }
        }
    }

    private List<Event> createEventsToProcess(List<Class<? extends RenderableView>> viewBranch, Model model) {
        List<Event> events = new ArrayList<>();
        Model parentModel = model;
        for (int i = 0; i < viewBranch.size(); ++i) {
            if (i + 1 == viewBranch.size()) {
                break;
            }

            Class<? extends RenderableView> viewI = viewBranch.get(i);
            Class<? extends RenderableView> viewI1 = viewBranch.get(i + 1);
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
            for (Class<? extends RenderableView> aClass : viewBranch) {
                ShowViewEvent event = new ShowViewEvent(aClass, null);
                events.add(event);
            }
        }

        return events;
    }

    private List<Event> createUnRenderViewEventList(List<Class<? extends RenderableView>> viewsToShow) {
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

    private boolean isViewExistsOnScreen(Class<? extends RenderableView> view) {
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

    public void registerRenderedView(Class<? extends RenderableView> classViewToRegister) {
        viewsOnTheScreen.add(classViewToRegister);
    }

    public void unregisterUnRenderedView(Class<? extends RenderableView> classViewToUnRegister) {
        viewsOnTheScreen.remove(classViewToUnRegister);
    }
}
