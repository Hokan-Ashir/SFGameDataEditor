package sfgamedataeditor.common.viewconfigurations.spellparameters.configurations;

import sfgamedataeditor.common.viewconfigurations.spellparameters.GUIElements;
import sfgamedataeditor.common.widgets.AbstractWidget;
import sfgamedataeditor.common.widgets.AbstractWidgetListener;
import sfgamedataeditor.common.widgets.effectnumber.EffectNumberListener;
import sfgamedataeditor.common.widgets.effectnumber.EffectNumberWidget;
import sfgamedataeditor.databind.Pair;

// TODO, note, is has the same position of EffectNumberWidget as ChainSpellsConfiguration
public class WavesSpellsConfiguration extends DefaultSpellParameterViewConfiguration {

    @Override
    protected void fillConfigurationMappings() {
        super.fillConfigurationMappings();
        addViewMapping(GUIElements.PARAMETER_4, new Pair<Class<? extends AbstractWidget>, Class<? extends AbstractWidgetListener>>(EffectNumberWidget.class, EffectNumberListener.class));
    }
}
