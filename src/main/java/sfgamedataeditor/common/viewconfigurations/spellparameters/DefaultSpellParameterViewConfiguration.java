package sfgamedataeditor.common.viewconfigurations.spellparameters;

import sfgamedataeditor.common.viewconfigurations.AbstractConfiguration;
import sfgamedataeditor.common.widgets.casttype.CastTypeForm;
import sfgamedataeditor.common.widgets.combobox.requirementclass.RequirementClassComboBoxWidget;
import sfgamedataeditor.common.widgets.common.text.TextFieldWidget;

public class DefaultSpellParameterViewConfiguration extends AbstractConfiguration {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fillConfigurationMappings() {
        addViewMapping(GUIElements.NUMBER, TextFieldWidget.class);
        addViewMapping(GUIElements.NAME_ID, TextFieldWidget.class);
        addViewMapping(GUIElements.REQUIREMENT_CLASS_1, RequirementClassComboBoxWidget.class);
        addViewMapping(GUIElements.REQUIREMENT_SUB_CLASS_1, RequirementClassComboBoxWidget.class);
        addViewMapping(GUIElements.REQUIREMENT_LEVEL_1, TextFieldWidget.class);
        addViewMapping(GUIElements.REQUIREMENT_CLASS_2, RequirementClassComboBoxWidget.class);
        addViewMapping(GUIElements.REQUIREMENT_SUB_CLASS_2, RequirementClassComboBoxWidget.class);
        addViewMapping(GUIElements.REQUIREMENT_LEVEL_2, TextFieldWidget.class);
        addViewMapping(GUIElements.REQUIREMENT_CLASS_3, RequirementClassComboBoxWidget.class);
        addViewMapping(GUIElements.REQUIREMENT_SUB_CLASS_3, RequirementClassComboBoxWidget.class);
        addViewMapping(GUIElements.REQUIREMENT_LEVEL_3, TextFieldWidget.class);
        addViewMapping(GUIElements.MANA_USAGE, TextFieldWidget.class);
        addViewMapping(GUIElements.CAST_TIME, TextFieldWidget.class);
        addViewMapping(GUIElements.COOLDOWN, TextFieldWidget.class);
        addViewMapping(GUIElements.MIN_RANGE, TextFieldWidget.class);
        addViewMapping(GUIElements.MAX_RANGE, TextFieldWidget.class);
        addViewMapping(GUIElements.CAST_TYPE, CastTypeForm.class);
        addViewMapping(GUIElements.PARAMETER_1, TextFieldWidget.class);
        addViewMapping(GUIElements.PARAMETER_2, TextFieldWidget.class);
        addViewMapping(GUIElements.PARAMETER_3, TextFieldWidget.class);
        addViewMapping(GUIElements.PARAMETER_4, TextFieldWidget.class);
        addViewMapping(GUIElements.PARAMETER_5, TextFieldWidget.class);
        addViewMapping(GUIElements.PARAMETER_6, TextFieldWidget.class);
        addViewMapping(GUIElements.PARAMETER_7, TextFieldWidget.class);
        addViewMapping(GUIElements.PARAMETER_8, TextFieldWidget.class);
        addViewMapping(GUIElements.PARAMETER_9, TextFieldWidget.class);
    }
}
