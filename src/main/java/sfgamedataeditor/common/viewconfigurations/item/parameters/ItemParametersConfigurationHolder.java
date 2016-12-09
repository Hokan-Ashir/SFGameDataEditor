package sfgamedataeditor.common.viewconfigurations.item.parameters;

import sfgamedataeditor.common.viewconfigurations.AbstractConfigurationHolder;

public class ItemParametersConfigurationHolder extends AbstractConfigurationHolder {
    @Override
    protected void fillConfigurationMapping() {
        addConfiguration(null, new ItemParametersViewConfiguration());
    }
}
