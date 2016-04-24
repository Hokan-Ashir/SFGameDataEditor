package sfgamedataeditor.views;

import sfgamedataeditor.ViewRegister;
import sfgamedataeditor.events.EventHandler;
import sfgamedataeditor.views.main.MainViewMetaEvent;

public class TopEventHandler {

    @EventHandler
    public void onShowModulesView(MainViewMetaEvent event) {
        ViewRegister.INSTANCE.process(event);
    }
}
