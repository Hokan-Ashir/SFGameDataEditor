package sfgamedataeditor.views.main;

import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.ShowViewEvent;
import sfgamedataeditor.views.common.AbstractView;

public class ShowMainViewEvent extends ShowViewEvent<MainView, AbstractView, Object> {
    public ShowMainViewEvent(ClassTuple<MainView, AbstractView> tuple) {
        super(tuple);
    }
}
