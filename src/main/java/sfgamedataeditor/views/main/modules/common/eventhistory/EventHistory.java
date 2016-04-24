package sfgamedataeditor.views.main.modules.common.eventhistory;

import sfgamedataeditor.events.AbstractMetaEvent;
import sfgamedataeditor.events.EventHandlerRegister;

import java.util.ArrayList;
import java.util.List;

public enum EventHistory {
    INSTANCE;

    private List<AbstractMetaEvent> events = new ArrayList<>();
    private int currentEventIndex = -1;

    public boolean addEventToHistory(AbstractMetaEvent metaEvent) {
        boolean isSuccess = events.add(metaEvent);
        currentEventIndex++;
        return isSuccess;
    }

    public void undo() {
        fireCurrentEvent();
        currentEventIndex--;
    }

    public void redo() {
        fireCurrentEvent();
        currentEventIndex++;
    }

    private void fireCurrentEvent() {
        AbstractMetaEvent event = events.get(currentEventIndex);
        EventHandlerRegister.INSTANCE.fireEventSilently(event);
    }

    public boolean isRedoPossible() {
        return currentEventIndex != events.size() - 1;
    }

    public boolean isUndoPossible() {
        return currentEventIndex != -1;
    }
}
