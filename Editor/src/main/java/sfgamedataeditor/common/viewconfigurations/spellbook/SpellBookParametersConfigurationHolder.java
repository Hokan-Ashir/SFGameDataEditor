package sfgamedataeditor.common.viewconfigurations.spellbook;

import sfgamedataeditor.common.viewconfigurations.AbstractConfigurationHolder;

public class SpellBookParametersConfigurationHolder extends AbstractConfigurationHolder {
    @Override
    protected void fillConfigurationMapping() {
        addConfiguration(null, new SpellBookParametersViewConfiguration());
    }
}
