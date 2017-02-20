package sfgamedataeditor.common.viewconfigurations.item.armor;

import sfgamedataeditor.common.viewconfigurations.AbstractConfigurationHolder;

public class ArmorParametersConfigurationHolder extends AbstractConfigurationHolder {
    @Override
    protected void fillConfigurationMapping() {
        addConfiguration(null, new ArmorParametersViewConfiguration());
    }
}
