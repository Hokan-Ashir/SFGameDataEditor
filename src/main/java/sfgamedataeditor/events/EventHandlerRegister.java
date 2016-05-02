package sfgamedataeditor.events;

import sfgamedataeditor.events.processing.ViewRegister;
import sfgamedataeditor.events.types.AbstractMetaEvent;
import sfgamedataeditor.views.main.MainView;
import sfgamedataeditor.views.main.modules.common.eventhistory.EventHistory;
import sfgamedataeditor.views.main.modules.common.eventhistory.EventHistoryView;

public enum EventHandlerRegister {
    INSTANCE;

    private EventBus eventBus = new EventBus();

    public void addEventHandler(Object eventHandler) {
        eventBus.addHandler(eventHandler);
    }

    public <T extends AbstractMetaEvent> void fireEvent(T event) {
        EventHistory.INSTANCE.addEventToHistory(event);
        eventBus.fireEvent(event);
        updateEventHistoryButtonsState();
    }

    public <T extends AbstractMetaEvent> void fireEventSilently(T event) {
        eventBus.fireEvent(event);
        updateEventHistoryButtonsState();
    }

    private void updateEventHistoryButtonsState() {
        EventHistoryView view = (EventHistoryView) ViewRegister.INSTANCE.getView(new ClassTuple<>(EventHistoryView.class, MainView.class));
        view.updateData(null);
    }
}
