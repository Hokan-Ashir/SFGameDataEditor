package sfgamedataeditor.common.viewconfigurations.spellparameters;

import sfgamedataeditor.common.viewconfigurations.AbstractConfiguration;
import sfgamedataeditor.common.widgets.AbstractWidget;
import sfgamedataeditor.common.widgets.casttype.CastTypeFormListener;
import sfgamedataeditor.common.widgets.casttype.CastTypeWidget;
import sfgamedataeditor.common.widgets.combobox.level.LevelComboBoxListener;
import sfgamedataeditor.common.widgets.combobox.level.LevelComboBoxWidget;
import sfgamedataeditor.common.widgets.combobox.requirementclass.RequirementClassSubClassListener;
import sfgamedataeditor.common.widgets.combobox.requirementclass.RequirementClassSubClassWidget;
import sfgamedataeditor.common.widgets.textfield.TextFieldListener;
import sfgamedataeditor.common.widgets.textfield.TextFieldWidget;
import sfgamedataeditor.databind.Pair;
import sfgamedataeditor.fieldwrapping.AbstractFieldListener;

public class DefaultSpellParameterViewConfiguration extends AbstractConfiguration {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fillConfigurationMappings() {
        addViewMapping(GUIElements.NUMBER, new Pair<Class<? extends AbstractWidget>, Class<? extends AbstractFieldListener>>(TextFieldWidget.class, TextFieldListener.class));
        addViewMapping(GUIElements.NAME_ID, new Pair<Class<? extends AbstractWidget>, Class<? extends AbstractFieldListener>>(TextFieldWidget.class, TextFieldListener.class));
        addViewMapping(GUIElements.SPELL_LEVEL, new Pair<Class<? extends AbstractWidget>, Class<? extends AbstractFieldListener>>(LevelComboBoxWidget.class, LevelComboBoxListener.class));
        addViewMapping(GUIElements.REQUIREMENT_CLASS_SUBCLASS_1, new Pair<Class<? extends AbstractWidget>, Class<? extends AbstractFieldListener>>(RequirementClassSubClassWidget.class, RequirementClassSubClassListener.class));
        addViewMapping(GUIElements.REQUIREMENT_LEVEL_1, new Pair<Class<? extends AbstractWidget>, Class<? extends AbstractFieldListener>>(TextFieldWidget.class, TextFieldListener.class));
        addViewMapping(GUIElements.REQUIREMENT_CLASS_SUBCLASS_2, new Pair<Class<? extends AbstractWidget>, Class<? extends AbstractFieldListener>>(RequirementClassSubClassWidget.class, RequirementClassSubClassListener.class));
        addViewMapping(GUIElements.REQUIREMENT_LEVEL_2, new Pair<Class<? extends AbstractWidget>, Class<? extends AbstractFieldListener>>(TextFieldWidget.class, TextFieldListener.class));
        addViewMapping(GUIElements.REQUIREMENT_CLASS_SUBCLASS_3, new Pair<Class<? extends AbstractWidget>, Class<? extends AbstractFieldListener>>(RequirementClassSubClassWidget.class, RequirementClassSubClassListener.class));
        addViewMapping(GUIElements.REQUIREMENT_LEVEL_3, new Pair<Class<? extends AbstractWidget>, Class<? extends AbstractFieldListener>>(TextFieldWidget.class, TextFieldListener.class));
        addViewMapping(GUIElements.MANA_USAGE, new Pair<Class<? extends AbstractWidget>, Class<? extends AbstractFieldListener>>(TextFieldWidget.class, TextFieldListener.class));
        addViewMapping(GUIElements.CAST_TIME, new Pair<Class<? extends AbstractWidget>, Class<? extends AbstractFieldListener>>(TextFieldWidget.class, TextFieldListener.class));
        addViewMapping(GUIElements.COOLDOWN, new Pair<Class<? extends AbstractWidget>, Class<? extends AbstractFieldListener>>(TextFieldWidget.class, TextFieldListener.class));
        addViewMapping(GUIElements.MIN_RANGE, new Pair<Class<? extends AbstractWidget>, Class<? extends AbstractFieldListener>>(TextFieldWidget.class, TextFieldListener.class));
        addViewMapping(GUIElements.MAX_RANGE, new Pair<Class<? extends AbstractWidget>, Class<? extends AbstractFieldListener>>(TextFieldWidget.class, TextFieldListener.class));
        addViewMapping(GUIElements.CAST_TYPE, new Pair<Class<? extends AbstractWidget>, Class<? extends AbstractFieldListener>>(CastTypeWidget.class, CastTypeFormListener.class));
        addViewMapping(GUIElements.PARAMETER_1, new Pair<Class<? extends AbstractWidget>, Class<? extends AbstractFieldListener>>(TextFieldWidget.class, TextFieldListener.class));
        addViewMapping(GUIElements.PARAMETER_2, new Pair<Class<? extends AbstractWidget>, Class<? extends AbstractFieldListener>>(TextFieldWidget.class, TextFieldListener.class));
        addViewMapping(GUIElements.PARAMETER_3, new Pair<Class<? extends AbstractWidget>, Class<? extends AbstractFieldListener>>(TextFieldWidget.class, TextFieldListener.class));
        addViewMapping(GUIElements.PARAMETER_4, new Pair<Class<? extends AbstractWidget>, Class<? extends AbstractFieldListener>>(TextFieldWidget.class, TextFieldListener.class));
        addViewMapping(GUIElements.PARAMETER_5, new Pair<Class<? extends AbstractWidget>, Class<? extends AbstractFieldListener>>(TextFieldWidget.class, TextFieldListener.class));
        addViewMapping(GUIElements.PARAMETER_6, new Pair<Class<? extends AbstractWidget>, Class<? extends AbstractFieldListener>>(TextFieldWidget.class, TextFieldListener.class));
        addViewMapping(GUIElements.PARAMETER_7, new Pair<Class<? extends AbstractWidget>, Class<? extends AbstractFieldListener>>(TextFieldWidget.class, TextFieldListener.class));
        addViewMapping(GUIElements.PARAMETER_8, new Pair<Class<? extends AbstractWidget>, Class<? extends AbstractFieldListener>>(TextFieldWidget.class, TextFieldListener.class));
        addViewMapping(GUIElements.PARAMETER_9, new Pair<Class<? extends AbstractWidget>, Class<? extends AbstractFieldListener>>(TextFieldWidget.class, TextFieldListener.class));
    }
}
