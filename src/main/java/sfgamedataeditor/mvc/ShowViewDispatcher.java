package sfgamedataeditor.mvc;

import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.EventHandlerRegister;
import sfgamedataeditor.events.processing.ViewRegister;
import sfgamedataeditor.events.types.Event;
import sfgamedataeditor.mvc.commonevents.ShowViewEvent;
import sfgamedataeditor.mvc.commonevents.UnShowViewEvent;
import sfgamedataeditor.mvc.commonevents.UpdateViewModelEvent;
import sfgamedataeditor.mvc.objects.Model;
import sfgamedataeditor.mvc.viewhierarchy.ViewHierarchy;
import sfgamedataeditor.views.common.AbstractView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum  ShowViewDispatcher {
    INSTANCE;

    private List<Class<? extends AbstractView>> viewsOnTheScreen = new ArrayList<>();
    private Map<ClassTuple<? extends AbstractView, ? extends AbstractView>, AbstractModelCreator> creatorMap = new HashMap<>();

    ShowViewDispatcher() {
//        creatorMap.put()
    }

    public void showView(Class<? extends AbstractView> viewClassToShow, Model model) {
        boolean viewExistsOnScreen = isViewExistsOnScreen(viewClassToShow);
        if (viewExistsOnScreen) {
            AbstractView view = ViewRegister.INSTANCE.getView(viewClassToShow);
            UpdateViewModelEvent updateViewModelEvent = new UpdateViewModelEvent(view, model);
            EventHandlerRegister.INSTANCE.fireEvent(updateViewModelEvent);
        } else {
            List<Class<? extends AbstractView>> viewsToShow = ViewHierarchy.INSTANCE.getViewsToShow(viewClassToShow);
            List<Event> eventsToProcess = createEventsToProcess(viewsToShow);
            List<Event> unrenderEvents = createUnRenderViewEventList(viewsToShow);
            eventsToProcess.addAll(unrenderEvents);
            for (Event eventsToProces : eventsToProcess) {
                EventHandlerRegister.INSTANCE.fireEventSilently(eventsToProces);
            }
        }
    }

    private List<Event> createEventsToProcess(List<Class<? extends AbstractView>> viewBranch) {
        List<Event> events = new ArrayList<>();
        // -2, not -1 for event before currently viewable view
        for (int i = viewBranch.size() - 2; i > -1; --i) {
            // TODO check border condition
            Class<? extends AbstractView> viewI = viewBranch.get(i);
            Class<? extends AbstractView> viewI1 = viewBranch.get(i + 1);
            boolean existsOnScreen = isViewExistsOnScreen(viewI);
            if (existsOnScreen) {
                AbstractView view = ViewRegister.INSTANCE.getView(viewI);
                AbstractModelCreator abstractModelCreator = creatorMap.get(new ClassTuple<>(viewI, viewI1));
                Model model = abstractModelCreator.createModel();
                UpdateViewModelEvent updateViewModelEvent = new UpdateViewModelEvent(view, model);
                events.add(updateViewModelEvent);
            } else {
                ShowViewEvent showViewEvent = new ShowViewEvent(viewI);
                events.add(showViewEvent);
            }
        }

        return events;
    }

    private List<Event> createUnRenderViewEventList(List<Class<? extends AbstractView>> viewsToShow) {
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

    private boolean isViewExistsOnScreen(Class<? extends AbstractView> view) {
        return viewsOnTheScreen.contains(view);
    }
}
