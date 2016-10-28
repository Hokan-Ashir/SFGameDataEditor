package sfgamedataeditor.views.main.modules.common.eventhistory;

import sfgamedataeditor.events.EventHandlerRegister;
import sfgamedataeditor.events.types.AbstractMetaEvent;
import sfgamedataeditor.events.types.AbstractViewableMetaEvent;

import java.util.ArrayList;
import java.util.List;

public enum EventHistory {
    INSTANCE;

    private List<AbstractMetaEvent> events = new ArrayList<>();
    private int currentEventIndex = -1;

    public boolean addEventToHistory(AbstractMetaEvent metaEvent) {
        List<AbstractViewableMetaEvent> events = getEvents((AbstractViewableMetaEvent) metaEvent);
        boolean isSuccess = this.events.add(metaEvent);
        currentEventIndex++;
        return isSuccess;
    }

    private List<AbstractViewableMetaEvent> getEvents(AbstractViewableMetaEvent metaEvent) {
        List<AbstractViewableMetaEvent> events = new ArrayList<>();
        AbstractViewableMetaEvent viewableMetaEvent = metaEvent.createParentMetaEvent();
        if (viewableMetaEvent != null) {
            events.add(viewableMetaEvent);
            events.addAll(getEvents(viewableMetaEvent));
        }

        return events;
    }

    public void undo() {
        currentEventIndex--;
        fireCurrentEvent();
    }

    public void redo() {
        currentEventIndex++;
        fireCurrentEvent();
    }

    private void fireCurrentEvent() {
        AbstractMetaEvent event = events.get(currentEventIndex);
        EventHandlerRegister.INSTANCE.fireEventSilently(event);
    }

    public boolean isRedoPossible() {
        int nextIndex = currentEventIndex + 1;
        if (nextIndex > events.size() - 1) {
            return false;
        }

        return events.get(nextIndex) != null;
    }

    public boolean isUndoPossible() {
        int previousIndex = currentEventIndex - 1;
        if (previousIndex < 0) {
            return false;
        }

        return events.get(previousIndex) != null;
    }
}
