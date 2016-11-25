package sfgamedataeditor.common.viewconfigurations.spellparameters;

import sfgamedataeditor.common.viewconfigurations.AbstractConfigurationHolder;
import sfgamedataeditor.views.main.modules.spells.schools.spells.parameters.SpellParameterModel;
import sfgamedataeditor.views.main.modules.spells.schools.spells.parameters.SpellParameterModelParameter;

public class SpellParametersConfigurationHolder extends AbstractConfigurationHolder {

    private static final int FIRESHIEND_SPELL_ID = 12;
    private static final int ICESHIELD_SPELL_ID = 15;

    @Override
    protected void fillConfigurationMapping() {
        addConfiguration(null, new DefaultSpellParameterViewConfiguration());
        IceOrFireShieldConfiguration configuration = new IceOrFireShieldConfiguration();
        addConfiguration(new SpellParameterModel(new SpellParameterModelParameter(FIRESHIEND_SPELL_ID, 0)), configuration);
        addConfiguration(new SpellParameterModel(new SpellParameterModelParameter(ICESHIELD_SPELL_ID, 0)), configuration);
    }
}
