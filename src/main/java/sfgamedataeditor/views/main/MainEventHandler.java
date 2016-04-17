package sfgamedataeditor.views.main;

import sfgamedataeditor.ViewRegister;
import sfgamedataeditor.events.EventHandler;
import sfgamedataeditor.views.common.ShowLevelableViewEvent;
import sfgamedataeditor.views.common.ShowNotImplementedViewEvent;
import sfgamedataeditor.views.main.modules.ShowButtonsViewEvent;
import sfgamedataeditor.views.main.modules.ShowModulesViewEvent;

public class MainEventHandler {

    @EventHandler
    public void onShowModulesView(ShowModulesViewEvent event) {
        ViewRegister.INSTANCE.process(event);
    }

    @EventHandler
    public void onShowButtonsView(ShowButtonsViewEvent event) {
        ViewRegister.INSTANCE.process(event);
    }

    @EventHandler
    public void onShowLevelableView(ShowLevelableViewEvent event) {
        ViewRegister.INSTANCE.process(event);
    }

    @EventHandler
    public void onShowNotImplementedView(ShowNotImplementedViewEvent event) {
        ViewRegister.INSTANCE.process(event);
    }
}
