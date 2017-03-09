package sfgamedataeditor.common.viewconfigurations.objects.chests;

import sfgamedataeditor.common.viewconfigurations.AbstractConfigurationHolder;

public class ChestLootParametersConfigurationHolder extends AbstractConfigurationHolder {
    @Override
    protected void fillConfigurationMapping() {
        addConfiguration(null, new ChestLootParametersViewConfiguration());
    }
}
