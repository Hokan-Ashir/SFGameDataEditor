package sfgamedataeditor.common.viewconfigurations.unit.parameters;

import sfgamedataeditor.common.viewconfigurations.AbstractConfigurationHolder;

public class UnitParametersConfigurationHolder extends AbstractConfigurationHolder {
    @Override
    protected void fillConfigurationMapping() {
        addConfiguration(null, new UnitParametersViewConfiguration());
    }
}
