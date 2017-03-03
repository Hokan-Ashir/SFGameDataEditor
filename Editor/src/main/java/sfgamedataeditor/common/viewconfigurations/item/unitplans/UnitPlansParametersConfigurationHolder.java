package sfgamedataeditor.common.viewconfigurations.item.unitplans;

import sfgamedataeditor.common.viewconfigurations.AbstractConfigurationHolder;

public class UnitPlansParametersConfigurationHolder extends AbstractConfigurationHolder {
    @Override
    protected void fillConfigurationMapping() {
        addConfiguration(null, new UnitPlansParametersViewConfiguration());
    }
}
