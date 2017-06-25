package sfgamedataeditor.common.viewconfigurations.player.level.stats;

import sfgamedataeditor.common.viewconfigurations.AbstractConfiguration;
import sfgamedataeditor.common.viewconfigurations.ConfigurationWidgetParameter;
import sfgamedataeditor.common.widgets.common.combobox.level.LevelComboBoxWidget;
import sfgamedataeditor.common.widgets.common.combobox.level.PlayerLevelStatsComboBoxListener;
import sfgamedataeditor.common.widgets.common.textfield.TextFieldWidget;
import sfgamedataeditor.common.widgets.common.textfield.TextFieldWidgetListener;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.util.HashMap;
import java.util.Map;

public class PlayerLevelStatsViewConfiguration extends AbstractConfiguration {
    @Override
    protected void fillConfigurationMappings() {
        addTextFieldWidgets();
        addLevelComboBoxWidgets();
    }

    private void addLevelComboBoxWidgets() {
        ConfigurationWidgetParameter parameter = new ConfigurationWidgetParameter(LevelComboBoxWidget.class, PlayerLevelStatsComboBoxListener.class, I18NTypes.PLAYER_LEVEL_STATS_GUI, "level");
        addViewMapping(GUIElements.LEVEL, parameter);
    }

    private void addTextFieldWidgets() {
        Map<Integer, String> i18nMap = new HashMap<Integer, String>() {{
            put(GUIElements.HP_FACTOR, "hpFactor");
            put(GUIElements.MP_FACTOR, "mpFactor");
            put(GUIElements.EXPERIENCE, "experience");
            put(GUIElements.MAX_ATTRIBUTES_POINTS, "maxAttributesPoints");
            put(GUIElements.MAX_SKILL_LEVEL, "maxSkillLevel");
            put(GUIElements.WEAPON_FACTOR, "weaponFactor");
            put(GUIElements.ARMOR_FACTOR, "armorFactor");
        }};

        for (Map.Entry<Integer, String> entry : i18nMap.entrySet()) {
            ConfigurationWidgetParameter parameter = new ConfigurationWidgetParameter(TextFieldWidget.class, TextFieldWidgetListener.class, I18NTypes.PLAYER_LEVEL_STATS_GUI, entry.getValue());
            addViewMapping(entry.getKey(), parameter);
        }
    }
}
