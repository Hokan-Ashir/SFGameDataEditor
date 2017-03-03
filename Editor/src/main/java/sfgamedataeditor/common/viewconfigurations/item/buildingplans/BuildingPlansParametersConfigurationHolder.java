package sfgamedataeditor.common.viewconfigurations.item.buildingplans;

import sfgamedataeditor.common.viewconfigurations.AbstractConfigurationHolder;

public class BuildingPlansParametersConfigurationHolder extends AbstractConfigurationHolder {
    @Override
    protected void fillConfigurationMapping() {
        addConfiguration(null, new BuildingPlansParametersViewConfiguration());
    }
}
