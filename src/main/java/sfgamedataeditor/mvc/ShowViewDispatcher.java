package sfgamedataeditor.mvc;

import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.processing.EventProcessor;
import sfgamedataeditor.events.processing.ViewRegister;
import sfgamedataeditor.events.types.Event;
import sfgamedataeditor.mvc.commonevents.ShowViewEvent;
import sfgamedataeditor.mvc.commonevents.UnShowViewEvent;
import sfgamedataeditor.mvc.commonevents.UpdateViewModelEvent;
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
    private Map<ClassTuple<? extends RenderableView, ? extends RenderableView>, AbstractModelCreator> creatorMap = new HashMap<>();

    ShowViewDispatcher() {
//        creatorMap.put()
    }

    public void showView(Class<? extends RenderableView> viewClassToShow, Model model) {
        boolean viewExistsOnScreen = isViewExistsOnScreen(viewClassToShow);
        if (viewExistsOnScreen) {
            RenderableView view = ViewRegister.INSTANCE.getView(viewClassToShow);
            UpdateViewModelEvent updateViewModelEvent = new UpdateViewModelEvent(view, model);
            EventHistory.INSTANCE.addEventToHistory(updateViewModelEvent);
            EventProcessor.INSTANCE.process(updateViewModelEvent);
            updateEventHistoryButtonsState();
        } else {
            List<Class<? extends RenderableView>> viewsToShow = ViewHierarchy.INSTANCE.getViewsToShow(viewClassToShow);
            List<Event> eventsToProcess = createEventsToProcess(viewsToShow);
            List<Event> unrenderEvents = createUnRenderViewEventList(viewsToShow);
            eventsToProcess.addAll(unrenderEvents);
            for (Event eventsToProces : eventsToProcess) {
                EventProcessor.INSTANCE.process(eventsToProces);
            }
        }
    }

    private List<Event> createEventsToProcess(List<Class<? extends RenderableView>> viewBranch) {
        List<Event> events = new ArrayList<>();
        // -2, not -1 for event before currently viewable view
        for (int i = viewBranch.size() - 2; i > -1; --i) {
            // TODO check border condition
            Class<? extends RenderableView> viewI = viewBranch.get(i);
            Class<? extends RenderableView> viewI1 = viewBranch.get(i + 1);
            boolean existsOnScreen = isViewExistsOnScreen(viewI);
            if (existsOnScreen) {
                RenderableView view = ViewRegister.INSTANCE.getView(viewI);
                AbstractModelCreator abstractModelCreator = creatorMap.get(new ClassTuple<>(viewI, viewI1));
                Model model = abstractModelCreator.createModel();
                UpdateViewModelEvent updateViewModelEvent = new UpdateViewModelEvent(view, model);
                events.add(updateViewModelEvent);
            } else {
                ShowViewEvent showViewEvent = new ShowViewEvent(viewI);
                events.add(showViewEvent);
            }
        }

        if (events.size() == 0 && viewBranch.size() != 0) {
            for (Class<? extends RenderableView> aClass : viewBranch) {
                ShowViewEvent event = new ShowViewEvent(aClass);
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
        }

        return unrenderEvents;
    }

    private boolean isViewExistsOnScreen(Class<? extends RenderableView> view) {
        return viewsOnTheScreen.contains(view);
    }

    private void updateEventHistoryButtonsState() {
        EventHistoryView view = ViewRegister.INSTANCE.getView(EventHistoryView.class);
        boolean isRedoPossible = EventHistory.INSTANCE.isRedoPossible();
        boolean isUndoPossible = EventHistory.INSTANCE.isUndoPossible();
        EventHistoryModelParameter parameter = new EventHistoryModelParameter(isRedoPossible, isUndoPossible);
        EventHistoryModel model = new EventHistoryModel(parameter);
        UpdateViewModelEvent event = new UpdateViewModelEvent(view, model);
        EventProcessor.INSTANCE.process(event);
    }
}
