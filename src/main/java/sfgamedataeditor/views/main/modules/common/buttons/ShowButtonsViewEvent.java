package sfgamedataeditor.views.main.modules.common.buttons;

import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.types.ShowViewEvent;
import sfgamedataeditor.views.main.MainView;

public class ShowButtonsViewEvent extends ShowViewEvent<ButtonsView, MainView, Object> {
    public ShowButtonsViewEvent(ClassTuple<ButtonsView, MainView> tuple) {
        super(tuple);
    }
}
