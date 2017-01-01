package sfgamedataeditor.common.viewconfigurations.creature.parameters;

import sfgamedataeditor.common.viewconfigurations.AbstractConfiguration;
import sfgamedataeditor.common.viewconfigurations.ConfigurationWidgetParameter;
import sfgamedataeditor.common.widgets.common.effectnumber.EffectNumberWidget;
import sfgamedataeditor.common.widgets.common.effectnumber.EffectNumberWidgetListener;
import sfgamedataeditor.common.widgets.common.textfield.TextFieldWidget;
import sfgamedataeditor.common.widgets.common.textfield.TextFieldWidgetListener;
import sfgamedataeditor.common.widgets.creatures.equipment.EquipmentWidget;
import sfgamedataeditor.common.widgets.creatures.equipment.EquipmentWidgetListener;
import sfgamedataeditor.common.widgets.creatures.equipmentslot.EquipmentSlotWidget;
import sfgamedataeditor.common.widgets.creatures.equipmentslot.EquipmentSlotWidgetListener;
import sfgamedataeditor.common.widgets.creatures.races.RacesWidget;
import sfgamedataeditor.common.widgets.creatures.races.RacesWidgetListener;
import sfgamedataeditor.common.widgets.creatures.vulnerability.VulnerabilityWidget;
import sfgamedataeditor.common.widgets.creatures.vulnerability.VulnerabilityWidgetListener;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.util.HashMap;
import java.util.Map;

public class CreatureParametersViewConfiguration extends AbstractConfiguration {
    @Override
    protected void fillConfigurationMappings() {
        addTextFieldWidgets();
        addRaceWidgets();
        addVulnerabilityWidgets();
        addEquipmentSlotsWidgets();
        addEquipmentWidgets();
        addSpellEffectNumberWidgets();
    }

    private void addSpellEffectNumberWidgets() {
        ConfigurationWidgetParameter spells = new ConfigurationWidgetParameter(EffectNumberWidget.class, EffectNumberWidgetListener.class,
                I18NTypes.CREATURES_GUI, "spells.spell.name");
        addViewMapping(GUIElements.SPELL1, spells);
        addViewMapping(GUIElements.SPELL2, spells);
        addViewMapping(GUIElements.SPELL3, spells);
    }

    private void addEquipmentWidgets() {
        ConfigurationWidgetParameter equipment = new ConfigurationWidgetParameter(EquipmentWidget.class, EquipmentWidgetListener.class,
                I18NTypes.CREATURES_GUI, "equipment.slot.item.type", "equipment.slot.item.name");
        addViewMapping(GUIElements.HEAD_SLOT, equipment);
        addViewMapping(GUIElements.CHEST_SLOT, equipment);
        addViewMapping(GUIElements.RIGHT_HAND_SLOT, equipment);
        addViewMapping(GUIElements.LEFT_HAND_SLOT, equipment);
        addViewMapping(GUIElements.RIGHT_RING_SLOT, equipment);
        addViewMapping(GUIElements.LEFT_RING_SLOT, equipment);
        addViewMapping(GUIElements.LEGS_SLOT, equipment);

        addViewMapping(GUIElements.DROP_ITEM_1, equipment);
        addViewMapping(GUIElements.DROP_ITEM_2, equipment);
        addViewMapping(GUIElements.DROP_ITEM_3, equipment);
    }

    private void addEquipmentSlotsWidgets() {
        ConfigurationWidgetParameter equipmentSlots = new ConfigurationWidgetParameter(EquipmentSlotWidget.class, EquipmentSlotWidgetListener.class,
                I18NTypes.CREATURES_GUI, "equipmentSlotsId", "equipmentSlotsId.all.slots.available",
                "equipmentSlotsId.hands.and.rings.slots.available", "equipmentSlotsId.no.slots.available");
        addViewMapping(GUIElements.EQUIPMENT_SLOTS_ID, equipmentSlots);
    }

    private void addVulnerabilityWidgets() {
        ConfigurationWidgetParameter vulnerability = new ConfigurationWidgetParameter(VulnerabilityWidget.class, VulnerabilityWidgetListener.class,
                I18NTypes.CREATURES_GUI, "vulnerability",
                "vulnerability.male.can.be.killed", "vulnerability.female.can.be.killed",
                "vulnerability.male.cannot.be.killed", "vulnerability.female.cannot.be.killed");
        addViewMapping(GUIElements.GENDER_AND_VULNERABILITY, vulnerability);
    }

    private void addRaceWidgets() {
        ConfigurationWidgetParameter raceIds = new ConfigurationWidgetParameter(RacesWidget.class, RacesWidgetListener.class, I18NTypes.CREATURES_GUI,
                "raceId");
        addViewMapping(GUIElements.RACE_ID, raceIds);
    }

    private void addTextFieldWidgets() {
        Map<Integer, String> i18nMap = new HashMap<Integer, String>() {{
            put(GUIElements.STATS_ID, "statsId");
            put(GUIElements.LEVEL, "level");
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
            put(GUIElements.EXPERIENCE, "experience");
            put(GUIElements.ARMOR, "armor");

            put(GUIElements.DROP_POSSIBILITY_ITEM_1, "loot.drop.possibility");
            put(GUIElements.DROP_POSSIBILITY_ITEM_2, "loot.drop.possibility");
        }};

        for (Map.Entry<Integer, String> entry : i18nMap.entrySet()) {
            ConfigurationWidgetParameter parameter = new ConfigurationWidgetParameter(TextFieldWidget.class, TextFieldWidgetListener.class, I18NTypes.CREATURES_GUI, entry.getValue());
            addViewMapping(entry.getKey(), parameter);
        }
    }
}
