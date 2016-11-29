package sfgamedataeditor.common.viewconfigurations.creatureparameters;

import sfgamedataeditor.common.viewconfigurations.AbstractConfiguration;
import sfgamedataeditor.common.viewconfigurations.ConfigurationWidgetParameter;
import sfgamedataeditor.common.widgets.textfield.TextFieldWidget;
import sfgamedataeditor.common.widgets.textfield.TextFieldWidgetListener;

import java.util.HashMap;
import java.util.Map;

public class CreatureParametersViewConfiguration extends AbstractConfiguration {
    @Override
    protected void fillConfigurationMappings() {
        // TODO set correct i18N
        Map<Integer, String> i18nMap = new HashMap<Integer, String>() {{
            put(GUIElements.NAME, "strength");
            put(GUIElements.STATS_ID, "strength");
            put(GUIElements.LEVEL, "strength");
            put(GUIElements.RACE_ID, "strength");
            put(GUIElements.AGILITY, "strength");
            put(GUIElements.CHARISMA, "strength");
            put(GUIElements.DEXTERITY, "strength");
            put(GUIElements.INTELLIGENCE, "strength");
            put(GUIElements.STAMINA, "strength");
            put(GUIElements.STRENGTH, "strength");
            put(GUIElements.WISDOM, "strength");
            put(GUIElements.FIRE_RESISTANCE, "strength");
            put(GUIElements.ICE_RESISTANCE, "strength");
            put(GUIElements.BLACK_RESISTANCE, "strength");
            put(GUIElements.MIND_RESISTANCE, "strength");
            put(GUIElements.WALK_SPEED, "strength");
            put(GUIElements.FIGHT_SPEED, "strength");
            put(GUIElements.CAST_SPEED, "strength");
            put(GUIElements.SIZE, "strength");
            put(GUIElements.SPAWN_TIME, "strength");
            put(GUIElements.GENDER_AND_VULNERABILITY, "strength");
            put(GUIElements.HEAD_ID, "strength");
            put(GUIElements.EQUIPMENT_SLOTS_ID, "strength");
        }};

        for (Map.Entry<Integer, String> entry : i18nMap.entrySet()) {
            ConfigurationWidgetParameter parameter = new ConfigurationWidgetParameter(TextFieldWidget.class, TextFieldWidgetListener.class, entry.getValue());
            addViewMapping(entry.getKey(), parameter);
        }
    }
}
