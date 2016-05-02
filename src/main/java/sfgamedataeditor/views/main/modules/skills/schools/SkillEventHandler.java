package sfgamedataeditor.views.main.modules.skills.schools;

import sfgamedataeditor.events.EventHandler;
import sfgamedataeditor.events.processing.EventProcessor;
import sfgamedataeditor.views.main.modules.skills.schools.parameters.SkillParametersMetaEvent;

public class SkillEventHandler {

    @EventHandler
    public void onShowSkillParameterView(SkillParametersMetaEvent event) {
        EventProcessor.INSTANCE.process(event);
    }
}
