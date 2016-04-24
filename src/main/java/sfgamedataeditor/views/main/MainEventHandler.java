package sfgamedataeditor.views.main;

import sfgamedataeditor.ViewRegister;
import sfgamedataeditor.events.EventHandler;
import sfgamedataeditor.views.common.notimplemented.NotImplementedMetaEvent;
import sfgamedataeditor.views.main.modules.common.ModulesMetaEvent;

public class MainEventHandler {

    @EventHandler
    public void onShowModulesView(ModulesMetaEvent event) {
        ViewRegister.INSTANCE.process(event);
    }

    @EventHandler
    public void onShowNotImplementedView(NotImplementedMetaEvent event) {
        ViewRegister.INSTANCE.process(event);
    }
}
