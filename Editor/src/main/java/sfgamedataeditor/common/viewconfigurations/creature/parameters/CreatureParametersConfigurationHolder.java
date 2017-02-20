package sfgamedataeditor.common.viewconfigurations.creature.parameters;

import sfgamedataeditor.common.viewconfigurations.AbstractConfigurationHolder;

public class CreatureParametersConfigurationHolder extends AbstractConfigurationHolder {
    @Override
    protected void fillConfigurationMapping() {
        addConfiguration(null, new CreatureParametersViewConfiguration());
    }
}
