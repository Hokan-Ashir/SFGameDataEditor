package sfgamedataeditor.views.main.modules.common.eventhistory;

import sfgamedataeditor.events.types.ShowViewEvent;
import sfgamedataeditor.mvc.ShowViewDispatcher;

import java.util.ArrayList;
import java.util.List;

public enum EventHistory {
    INSTANCE;

    private List<ShowViewEvent> events = new ArrayList<>();
    private int currentEventIndex = -1;

    public boolean addEventToHistory(ShowViewEvent event) {
        boolean isSuccess = this.events.add(event);
        currentEventIndex++;
        return isSuccess;
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
        ShowViewDispatcher.INSTANCE.showViewSilently(event.getViewClass(), event.getModel());
//        EventProcessor.INSTANCE.process(event);
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
