package sfgamedataeditor.views.main.modules.skills.schools;

import sfgamedataeditor.ViewRegister;
import sfgamedataeditor.events.EventHandler;
import sfgamedataeditor.views.main.modules.skills.schools.parameters.ShowSkillParameterViewEvent;

public class SkillEventHandler {

    @EventHandler
    public void onShowSkillParameterView(ShowSkillParameterViewEvent event) {
        ViewRegister.INSTANCE.process(event);
    }
}
