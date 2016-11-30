package sfgamedataeditor.common.viewconfigurations.spellparameters.configurations;

import sfgamedataeditor.common.viewconfigurations.AbstractConfiguration;
import sfgamedataeditor.common.viewconfigurations.ConfigurationWidgetParameter;
import sfgamedataeditor.common.viewconfigurations.spellparameters.GUIElements;
import sfgamedataeditor.common.widgets.casttype.CastTypeWidget;
import sfgamedataeditor.common.widgets.casttype.CastTypeWidgetListener;
import sfgamedataeditor.common.widgets.combobox.level.LevelComboBoxWidget;
import sfgamedataeditor.common.widgets.combobox.level.SpellLevelComboBoxListener;
import sfgamedataeditor.common.widgets.combobox.requirementclass.RequirementClassSubClassWidget;
import sfgamedataeditor.common.widgets.combobox.requirementclass.RequirementClassSubClassWidgetListener;
import sfgamedataeditor.common.widgets.textfield.TextFieldWidget;
import sfgamedataeditor.common.widgets.textfield.TextFieldWidgetListener;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

// TODO get rid of inheritance and maybe choose something like decoration
// TODO get rid of unnecessary fields creation and storing in cache (not each spell should have 9 additional textFieldWidgets, that should be hidden)
public class DefaultSpellParameterViewConfiguration extends AbstractConfiguration {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fillConfigurationMappings() {
        ConfigurationWidgetParameter number = new ConfigurationWidgetParameter(TextFieldWidget.class, TextFieldWidgetListener.class, I18NTypes.COMMON, "spellNumber");
        addViewMapping(GUIElements.NUMBER, number);

        ConfigurationWidgetParameter nameId = new ConfigurationWidgetParameter(TextFieldWidget.class, TextFieldWidgetListener.class, I18NTypes.COMMON, "spellType");
        addViewMapping(GUIElements.NAME_ID, nameId);

        ConfigurationWidgetParameter spellLevel = new ConfigurationWidgetParameter(LevelComboBoxWidget.class, SpellLevelComboBoxListener.class, I18NTypes.COMMON, "levelLabel");
        addViewMapping(GUIElements.SPELL_LEVEL, spellLevel);

        ConfigurationWidgetParameter requirementClass = new ConfigurationWidgetParameter(RequirementClassSubClassWidget.class, RequirementClassSubClassWidgetListener.class, I18NTypes.COMMON, "spellRequirementClass", "spellRequirementSubClass");
        ConfigurationWidgetParameter requirementLevel = new ConfigurationWidgetParameter(TextFieldWidget.class, TextFieldWidgetListener.class, I18NTypes.COMMON, "spellRequirementLevel");
        addViewMapping(GUIElements.REQUIREMENT_CLASS_SUBCLASS_1, requirementClass);
        addViewMapping(GUIElements.REQUIREMENT_LEVEL_1, requirementLevel);
        addViewMapping(GUIElements.REQUIREMENT_CLASS_SUBCLASS_2, requirementClass);
        addViewMapping(GUIElements.REQUIREMENT_LEVEL_2, requirementLevel);
        addViewMapping(GUIElements.REQUIREMENT_CLASS_SUBCLASS_3, requirementClass);
        addViewMapping(GUIElements.REQUIREMENT_LEVEL_3, requirementLevel);

        ConfigurationWidgetParameter manaUsage = new ConfigurationWidgetParameter(TextFieldWidget.class, TextFieldWidgetListener.class, I18NTypes.COMMON, "spellManaUsage");
        addViewMapping(GUIElements.MANA_USAGE, manaUsage);

        ConfigurationWidgetParameter spellCastTime = new ConfigurationWidgetParameter(TextFieldWidget.class, TextFieldWidgetListener.class, I18NTypes.COMMON, "spellCastTime");
        addViewMapping(GUIElements.CAST_TIME, spellCastTime);

        ConfigurationWidgetParameter cooldown = new ConfigurationWidgetParameter(TextFieldWidget.class, TextFieldWidgetListener.class, I18NTypes.COMMON, "spellCooldown");
        addViewMapping(GUIElements.COOLDOWN, cooldown);

        ConfigurationWidgetParameter minRange = new ConfigurationWidgetParameter(TextFieldWidget.class, TextFieldWidgetListener.class, I18NTypes.COMMON, "spellMinRange");
        addViewMapping(GUIElements.MIN_RANGE, minRange);

        ConfigurationWidgetParameter maxRange = new ConfigurationWidgetParameter(TextFieldWidget.class, TextFieldWidgetListener.class, I18NTypes.COMMON, "spellMaxRange");
        addViewMapping(GUIElements.MAX_RANGE, maxRange);

        ConfigurationWidgetParameter castType = new ConfigurationWidgetParameter(CastTypeWidget.class, CastTypeWidgetListener.class,
                I18NTypes.COMMON,
                "spellCastType", "spellCastType.projectileToEnemy","spellCastType.projectileToAllies", "spellCastType.targetArea",
                "spellCastType.worldInstantArea", "spellCastType.instantArea", "spellCastType.worldTargetArea", "spellCastType.alliesArea");
        addViewMapping(GUIElements.CAST_TYPE, castType);

        ConfigurationWidgetParameter parameter = new ConfigurationWidgetParameter(TextFieldWidget.class, TextFieldWidgetListener.class, I18NTypes.COMMON);
        addViewMapping(GUIElements.PARAMETER_1, parameter);
        addViewMapping(GUIElements.PARAMETER_2, parameter);
        addViewMapping(GUIElements.PARAMETER_3, parameter);
        addViewMapping(GUIElements.PARAMETER_4, parameter);
        addViewMapping(GUIElements.PARAMETER_5, parameter);
        addViewMapping(GUIElements.PARAMETER_6, parameter);
        addViewMapping(GUIElements.PARAMETER_7, parameter);
        addViewMapping(GUIElements.PARAMETER_8, parameter);
        addViewMapping(GUIElements.PARAMETER_9, parameter);
    }
}
