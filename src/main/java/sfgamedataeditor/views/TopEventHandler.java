package sfgamedataeditor.views;

import sfgamedataeditor.events.EventHandler;
import sfgamedataeditor.events.processing.EventProcessor;
import sfgamedataeditor.views.main.MainViewMetaEvent;

public class TopEventHandler {

    @EventHandler
    public void onShowModulesView(MainViewMetaEvent event) {
        EventProcessor.INSTANCE.process(event);
    }
}
