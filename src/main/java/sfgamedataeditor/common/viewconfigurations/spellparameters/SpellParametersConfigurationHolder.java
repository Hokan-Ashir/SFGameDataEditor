package sfgamedataeditor.common.viewconfigurations.spellparameters;

import sfgamedataeditor.common.viewconfigurations.AbstractConfigurationHolder;

public class SpellParametersConfigurationHolder extends AbstractConfigurationHolder {
    @Override
    protected void fillConfigurationMapping() {
        addConfiguration(null, new DefaultSpellParameterViewConfiguration());
    }
}
