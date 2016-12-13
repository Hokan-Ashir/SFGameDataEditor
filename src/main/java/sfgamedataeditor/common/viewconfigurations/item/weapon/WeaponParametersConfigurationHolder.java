package sfgamedataeditor.common.viewconfigurations.item.weapon;

import sfgamedataeditor.common.viewconfigurations.AbstractConfigurationHolder;

public class WeaponParametersConfigurationHolder extends AbstractConfigurationHolder {
    @Override
    protected void fillConfigurationMapping() {
        addConfiguration(null, new WeaponParametersViewConfiguration());
    }
}
