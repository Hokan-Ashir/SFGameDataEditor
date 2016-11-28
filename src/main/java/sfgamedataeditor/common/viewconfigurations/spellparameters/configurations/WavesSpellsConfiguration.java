package sfgamedataeditor.common.viewconfigurations.spellparameters.configurations;

import sfgamedataeditor.common.viewconfigurations.ConfigurationWidgetParameter;
import sfgamedataeditor.common.viewconfigurations.spellparameters.GUIElements;
import sfgamedataeditor.common.widgets.effectnumber.EffectNumberListener;
import sfgamedataeditor.common.widgets.effectnumber.EffectNumberWidget;

// TODO, note, is has the same position of EffectNumberWidget as ChainSpellsConfiguration
public class WavesSpellsConfiguration extends DefaultSpellParameterViewConfiguration {

    @Override
    protected void fillConfigurationMappings() {
        super.fillConfigurationMappings();
        // TODO add i18n
        ConfigurationWidgetParameter effectNumber = new ConfigurationWidgetParameter(EffectNumberWidget.class, EffectNumberListener.class);
        addViewMapping(GUIElements.PARAMETER_4, effectNumber);
    }
}
