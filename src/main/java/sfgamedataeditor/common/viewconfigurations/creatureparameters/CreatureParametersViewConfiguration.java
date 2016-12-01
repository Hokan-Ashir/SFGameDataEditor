package sfgamedataeditor.common.viewconfigurations.creatureparameters;

import sfgamedataeditor.common.viewconfigurations.AbstractConfiguration;
import sfgamedataeditor.common.viewconfigurations.ConfigurationWidgetParameter;
import sfgamedataeditor.common.widgets.equipmentslot.EquipmentSlotWidget;
import sfgamedataeditor.common.widgets.equipmentslot.EquipmentSlotWidgetListener;
import sfgamedataeditor.common.widgets.textfield.TextFieldWidget;
import sfgamedataeditor.common.widgets.textfield.TextFieldWidgetListener;
import sfgamedataeditor.common.widgets.vulnerability.VulnerabilityWidget;
import sfgamedataeditor.common.widgets.vulnerability.VulnerabilityWidgetListener;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.util.HashMap;
import java.util.Map;

public class CreatureParametersViewConfiguration extends AbstractConfiguration {
    @Override
    protected void fillConfigurationMappings() {
        Map<Integer, String> i18nMap = new HashMap<Integer, String>() {{
            put(GUIElements.STATS_ID, "statsId");
            put(GUIElements.LEVEL, "level");
            put(GUIElements.RACE_ID, "raceId");
            put(GUIElements.AGILITY, "agility");
            put(GUIElements.CHARISMA, "charisma");
            put(GUIElements.DEXTERITY, "dexterity");
            put(GUIElements.INTELLIGENCE, "intelligence");
            put(GUIElements.STAMINA, "stamina");
            put(GUIElements.STRENGTH, "strength");
            put(GUIElements.WISDOM, "wisdom");
            put(GUIElements.FIRE_RESISTANCE, "fireResistance");
            put(GUIElements.ICE_RESISTANCE, "iceResistance");
            put(GUIElements.BLACK_RESISTANCE, "blackResistance");
            put(GUIElements.MIND_RESISTANCE, "mindResistance");
            put(GUIElements.WALK_SPEED, "walkSpeed");
            put(GUIElements.FIGHT_SPEED, "fightSpeed");
            put(GUIElements.CAST_SPEED, "castSpeed");
            put(GUIElements.SIZE, "size");
            put(GUIElements.SPAWN_TIME, "spawnTime");
            put(GUIElements.HEAD_ID, "headId");
        }};

        for (Map.Entry<Integer, String> entry : i18nMap.entrySet()) {
            ConfigurationWidgetParameter parameter = new ConfigurationWidgetParameter(TextFieldWidget.class, TextFieldWidgetListener.class, I18NTypes.CREATURES_GUI, entry.getValue());
            addViewMapping(entry.getKey(), parameter);
        }

        ConfigurationWidgetParameter vulnerability = new ConfigurationWidgetParameter(VulnerabilityWidget.class, VulnerabilityWidgetListener.class,
                I18NTypes.CREATURES_GUI, "vulnerability",
                "vulnerability.male.can.be.killed", "vulnerability.female.can.be.killed",
                "vulnerability.male.cannot.be.killed", "vulnerability.female.cannot.be.killed");
        addViewMapping(GUIElements.GENDER_AND_VULNERABILITY, vulnerability);

        ConfigurationWidgetParameter equipmentSlots = new ConfigurationWidgetParameter(EquipmentSlotWidget.class, EquipmentSlotWidgetListener.class,
                I18NTypes.CREATURES_GUI, "equipmentSlotsId", "equipmentSlotsId.all.slots.available",
                "equipmentSlotsId.hands.and.rings.slots.available", "equipmentSlotsId.no.slots.available");
        addViewMapping(GUIElements.EQUIPMENT_SLOTS_ID, equipmentSlots);
    }
}
