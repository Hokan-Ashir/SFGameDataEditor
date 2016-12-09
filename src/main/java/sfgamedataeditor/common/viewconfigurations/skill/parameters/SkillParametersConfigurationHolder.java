package sfgamedataeditor.common.viewconfigurations.skill.parameters;

import sfgamedataeditor.common.viewconfigurations.AbstractConfigurationHolder;

public class SkillParametersConfigurationHolder extends AbstractConfigurationHolder {
    @Override
    protected void fillConfigurationMapping() {
        addConfiguration(null, new SkillParametersViewConfiguration());
    }
}
