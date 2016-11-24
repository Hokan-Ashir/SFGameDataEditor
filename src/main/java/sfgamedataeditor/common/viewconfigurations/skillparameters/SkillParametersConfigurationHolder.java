package sfgamedataeditor.common.viewconfigurations.skillparameters;

import sfgamedataeditor.common.viewconfigurations.AbstractConfigurationHolder;

public class SkillParametersConfigurationHolder extends AbstractConfigurationHolder {
    @Override
    protected void fillConfigurationMapping() {
        addConfiguration(null, new SkillParametersViewConfiguration());
    }
}
