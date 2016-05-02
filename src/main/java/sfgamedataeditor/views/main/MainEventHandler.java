package sfgamedataeditor.views.main;

import sfgamedataeditor.events.EventHandler;
import sfgamedataeditor.events.processing.EventProcessor;
import sfgamedataeditor.views.common.notimplemented.NotImplementedMetaEvent;
import sfgamedataeditor.views.main.modules.common.ModulesMetaEvent;

public class MainEventHandler {

    @EventHandler
    public void onShowModulesView(ModulesMetaEvent event) {
        EventProcessor.INSTANCE.process(event);
    }

    @EventHandler
    public void onShowNotImplementedView(NotImplementedMetaEvent event) {
        EventProcessor.INSTANCE.process(event);
    }
}
