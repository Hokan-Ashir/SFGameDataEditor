package sfgamedataeditor.views;

import sfgamedataeditor.ViewRegister;
import sfgamedataeditor.events.EventHandler;
import sfgamedataeditor.views.main.ShowMainViewEvent;

public class TopEventHandler {

    @EventHandler
    public void onShowModulesView(ShowMainViewEvent event) {
        ViewRegister.INSTANCE.process(event);
    }
}
