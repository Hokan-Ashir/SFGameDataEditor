package sfgamedataeditor.views.main.modules.common.eventhistory;

import sfgamedataeditor.events.processing.EventProcessor;
import sfgamedataeditor.events.types.ShowViewEvent;

import java.util.ArrayList;
import java.util.List;

public enum EventHistory {
    INSTANCE;

    private final List<ShowViewEvent> events = new ArrayList<>();
    private int currentEventIndex = -1;

    public void addEventToHistory(ShowViewEvent event) {
        this.events.add(event);
        currentEventIndex++;
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
        ShowViewEvent event = events.get(currentEventIndex);
        event.setShouldBeRecordedInHistory(false);
        EventProcessor.INSTANCE.process(event);
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
