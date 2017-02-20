package sfgamedataeditor.common.viewconfigurations.item.scrolls;

import sfgamedataeditor.common.viewconfigurations.AbstractConfigurationHolder;

public class ScrollsParametersConfigurationHolder extends AbstractConfigurationHolder {
    @Override
    protected void fillConfigurationMapping() {
        addConfiguration(null, new ScrollsParametersViewConfiguration());
    }
}
