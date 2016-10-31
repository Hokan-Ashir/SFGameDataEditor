package sfgamedataeditor.events;

import sfgamedataeditor.events.processing.ViewRegister;
import sfgamedataeditor.events.types.Event;
import sfgamedataeditor.mvc.commonevents.UpdateViewModelEvent;
import sfgamedataeditor.views.main.modules.common.eventhistory.EventHistory;
import sfgamedataeditor.views.main.modules.common.eventhistory.EventHistoryModel;
import sfgamedataeditor.views.main.modules.common.eventhistory.EventHistoryModelParameter;
import sfgamedataeditor.views.main.modules.common.eventhistory.EventHistoryView;

public enum EventHandlerRegister {
    INSTANCE;

    private EventBus eventBus = new EventBus();

    public void addEventHandler(Object eventHandler) {
        eventBus.addHandler(eventHandler);
    }

    public <T extends Event> void fireEvent(T event) {
        EventHistory.INSTANCE.addEventToHistory(event);
        eventBus.fireEvent(event);
        updateEventHistoryButtonsState();
    }

    public <T extends Event> void fireEventSilently(T event) {
        eventBus.fireEvent(event);
        updateEventHistoryButtonsState();
    }

    private void updateEventHistoryButtonsState() {
        EventHistoryView view = (EventHistoryView) ViewRegister.INSTANCE.getView(EventHistoryView.class);
        boolean isRedoPossible = EventHistory.INSTANCE.isRedoPossible();
        boolean isUndoPossible = EventHistory.INSTANCE.isUndoPossible();
        EventHistoryModelParameter parameter = new EventHistoryModelParameter(isRedoPossible, isUndoPossible);
        EventHistoryModel model = new EventHistoryModel(parameter);
        UpdateViewModelEvent event = new UpdateViewModelEvent(view, model);
        eventBus.fireEvent(event);
    }
}
