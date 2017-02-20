package sfgamedataeditor.common.viewconfigurations.item.miscellaneous;

import sfgamedataeditor.common.viewconfigurations.AbstractConfigurationHolder;

public class MiscellaneousParametersConfigurationHolder extends AbstractConfigurationHolder {
    @Override
    protected void fillConfigurationMapping() {
        addConfiguration(null, new MiscellaneousParametersViewConfiguration());
    }
}
