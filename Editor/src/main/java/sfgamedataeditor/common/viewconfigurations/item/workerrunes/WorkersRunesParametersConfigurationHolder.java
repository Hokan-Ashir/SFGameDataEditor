package sfgamedataeditor.common.viewconfigurations.item.workerrunes;

import sfgamedataeditor.common.viewconfigurations.AbstractConfigurationHolder;

public class WorkersRunesParametersConfigurationHolder extends AbstractConfigurationHolder {
    @Override
    protected void fillConfigurationMapping() {
        addConfiguration(null, new WorkersRunesParametersViewConfiguration());
    }
}
