package sfgamedataeditor.views.main.modules.common.modules;

import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.ShowViewEvent;
import sfgamedataeditor.views.main.MainView;

public class ShowModulesViewEvent extends ShowViewEvent<ModulesView, MainView, Object> {
    public ShowModulesViewEvent(ClassTuple<ModulesView, MainView> tuple) {
        super(tuple);
    }
}
