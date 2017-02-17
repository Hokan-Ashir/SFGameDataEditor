package sfgamedataeditor.common.viewconfigurations.buildings.parameters;

import sfgamedataeditor.common.viewconfigurations.AbstractConfigurationHolder;

public class BuildingsParametersConfigurationHolder extends AbstractConfigurationHolder {
    @Override
    protected void fillConfigurationMapping() {
        addConfiguration(null, new CreatureParametersViewConfiguration());
    }
}
