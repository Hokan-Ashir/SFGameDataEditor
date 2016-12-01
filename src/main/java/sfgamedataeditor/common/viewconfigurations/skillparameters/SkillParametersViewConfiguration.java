package sfgamedataeditor.common.viewconfigurations.skillparameters;

import sfgamedataeditor.common.viewconfigurations.AbstractConfiguration;
import sfgamedataeditor.common.viewconfigurations.ConfigurationWidgetParameter;
import sfgamedataeditor.common.widgets.combobox.level.LevelComboBoxWidget;
import sfgamedataeditor.common.widgets.combobox.level.SkillLevelComboBoxListener;
import sfgamedataeditor.common.widgets.textfield.TextFieldWidget;
import sfgamedataeditor.common.widgets.textfield.TextFieldWidgetListener;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.util.HashMap;
import java.util.Map;

public class SkillParametersViewConfiguration extends AbstractConfiguration {
    @Override
    protected void fillConfigurationMappings() {
        Map<Integer, String> i18nMap = new HashMap<Integer, String>() {{
            put(GUIElements.STRENGTH, "strength");
            put(GUIElements.STAMINA, "stamina");
            put(GUIElements.AGILITY, "agility");
            put(GUIElements.DEXTERITY, "dexterity");
            put(GUIElements.CHARISMA, "charisma");
            put(GUIElements.INTELLIGENCE, "intelligence");
            put(GUIElements.WISDOM, "wisdom");
        }};

        for (Map.Entry<Integer, String> entry : i18nMap.entrySet()) {
            ConfigurationWidgetParameter parameter = new ConfigurationWidgetParameter(TextFieldWidget.class, TextFieldWidgetListener.class, I18NTypes.SKILLS_GUI, entry.getValue());
            addViewMapping(entry.getKey(), parameter);
        }

        ConfigurationWidgetParameter levelParameter = new ConfigurationWidgetParameter(LevelComboBoxWidget.class, SkillLevelComboBoxListener.class, I18NTypes.SKILLS_GUI, "levelLabel");
        addViewMapping(GUIElements.SKILL_LEVEL, levelParameter);
    }
}
