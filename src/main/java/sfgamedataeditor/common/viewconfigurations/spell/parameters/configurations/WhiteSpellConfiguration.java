package sfgamedataeditor.common.viewconfigurations.spell.parameters.configurations;

import sfgamedataeditor.common.viewconfigurations.ConfigurationWidgetParameter;
import sfgamedataeditor.common.viewconfigurations.spell.parameters.GUIElements;
import sfgamedataeditor.common.widgets.common.effectnumber.EffectNumberWidget;
import sfgamedataeditor.common.widgets.common.effectnumber.EffectNumberWidgetListener;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

// TODO, note, is has the same position of EffectNumberWidget as ShieldsSpellsConfiguration
public class WhiteSpellConfiguration extends DefaultSpellParameterViewConfiguration {

    @Override
    protected void fillConfigurationMappings() {
        super.fillConfigurationMappings();
        // TODO add i18n
        ConfigurationWidgetParameter effectNumber = new ConfigurationWidgetParameter(EffectNumberWidget.class, EffectNumberWidgetListener.class, I18NTypes.SPELLS_GUI);
        addViewMapping(GUIElements.PARAMETER_2, effectNumber);
    }
}
