package sfgamedataeditor.views.main.modules.common.eventhistory;

import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.types.ShowViewEvent;
import sfgamedataeditor.views.main.MainView;

public class ShowEventHistoryViewEvent extends ShowViewEvent<EventHistoryView, MainView, Object> {
    public ShowEventHistoryViewEvent(
            ClassTuple<EventHistoryView, MainView> tuple) {
        super(tuple);
    }
}
