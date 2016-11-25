package sfgamedataeditor.common.viewconfigurations.spellparameters;

import sfgamedataeditor.common.widgets.AbstractWidget;
import sfgamedataeditor.common.widgets.AbstractWidgetListener;
import sfgamedataeditor.common.widgets.effectnumber.EffectNumberListener;
import sfgamedataeditor.common.widgets.effectnumber.EffectNumberWidget;
import sfgamedataeditor.databind.Pair;

public class IceOrFireShieldConfiguration extends DefaultSpellParameterViewConfiguration {

    @Override
    protected void fillConfigurationMappings() {
        super.fillConfigurationMappings();
        addViewMapping(GUIElements.PARAMETER_2, new Pair<Class<? extends AbstractWidget>, Class<? extends AbstractWidgetListener>>(EffectNumberWidget.class, EffectNumberListener.class));
    }
}