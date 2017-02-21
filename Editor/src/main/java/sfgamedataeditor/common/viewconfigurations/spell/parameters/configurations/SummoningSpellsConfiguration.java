package sfgamedataeditor.common.viewconfigurations.spell.parameters.configurations;

import sfgamedataeditor.common.viewconfigurations.ConfigurationWidgetParameter;
import sfgamedataeditor.common.viewconfigurations.spell.parameters.GUIElements;
import sfgamedataeditor.common.widgets.spells.summonedcreature.CreatureWidget;
import sfgamedataeditor.common.widgets.spells.summonedcreature.CreatureWidgetListener;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

public class SummoningSpellsConfiguration extends DefaultSpellParameterViewConfiguration {

    @Override
    protected void fillConfigurationMappings() {
        super.fillConfigurationMappings();
        ConfigurationWidgetParameter summonedCreature = new ConfigurationWidgetParameter(CreatureWidget.class, CreatureWidgetListener.class, I18NTypes.SPELLS_GUI);
        addViewMapping(GUIElements.PARAMETER_3, summonedCreature);
    }
}
