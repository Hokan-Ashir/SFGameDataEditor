package sfgamedataeditor.views.main.modules.skills.schools;

import sfgamedataeditor.ViewRegister;
import sfgamedataeditor.events.EventHandler;
import sfgamedataeditor.views.main.modules.skills.schools.parameters.SkillParametersMetaEvent;

public class SkillEventHandler {

    @EventHandler
    public void onShowSkillParameterView(SkillParametersMetaEvent event) {
        ViewRegister.INSTANCE.process(event);
    }
}
