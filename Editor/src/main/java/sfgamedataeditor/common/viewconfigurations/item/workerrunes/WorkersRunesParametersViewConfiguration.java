package sfgamedataeditor.common.viewconfigurations.item.workerrunes;

import sfgamedataeditor.common.viewconfigurations.AbstractConfiguration;
import sfgamedataeditor.common.viewconfigurations.ConfigurationWidgetParameter;
import sfgamedataeditor.common.widgets.common.combobox.level.LevelComboBoxWidget;
import sfgamedataeditor.common.widgets.common.combobox.level.WorkerRunesLevelComboBoxListener;
import sfgamedataeditor.common.widgets.common.textfield.TextFieldWidget;
import sfgamedataeditor.common.widgets.common.textfield.TextFieldWidgetListener;
import sfgamedataeditor.common.widgets.creatures.equipmentslot.EquipmentSlotWidget;
import sfgamedataeditor.common.widgets.creatures.equipmentslot.EquipmentSlotWidgetListener;
import sfgamedataeditor.common.widgets.creatures.vulnerability.VulnerabilityWidget;
import sfgamedataeditor.common.widgets.creatures.vulnerability.VulnerabilityWidgetListener;
import sfgamedataeditor.common.widgets.items.itemprice.ItemPriceWidget;
import sfgamedataeditor.common.widgets.items.itemprice.ItemPriceWidgetListener;
import sfgamedataeditor.common.widgets.items.itemset.ItemSetWidget;
import sfgamedataeditor.common.widgets.items.itemset.ItemSetWidgetListener;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.util.HashMap;
import java.util.Map;

public class WorkersRunesParametersViewConfiguration extends AbstractConfiguration {
    @Override
    protected void fillConfigurationMappings() {
        addTextFieldWidgets();
        addRaceWidgets();
        addVulnerabilityWidgets();
        addSkillsWidgets();
        addLevelWidget();
        addItemsWidgets();
        addEquipmentSlotsWidgets();
    }

    private void addItemsWidgets() {
        ConfigurationWidgetParameter buyoutPrice = new ConfigurationWidgetParameter(ItemPriceWidget.class, ItemPriceWidgetListener.class,
                I18NTypes.WORKERS_RUNES_GUI, "buyoutPrice", "goldAmount", "silverAmount", "copperAmount");
        addViewMapping(GUIElements.BUY_OUT_PRICE, buyoutPrice);

        ConfigurationWidgetParameter selloutPrice = new ConfigurationWidgetParameter(ItemPriceWidget.class, ItemPriceWidgetListener.class,
                I18NTypes.WORKERS_RUNES_GUI, "selloutPrice", "goldAmount", "silverAmount", "copperAmount");
        addViewMapping(GUIElements.SELL_PRICE, selloutPrice);

        ConfigurationWidgetParameter itemSet = new ConfigurationWidgetParameter(ItemSetWidget.class, ItemSetWidgetListener.class, I18NTypes.WORKERS_RUNES_GUI,
                "itemSet");
        addViewMapping(GUIElements.ITEM_SET, itemSet);
    }

    private void addVulnerabilityWidgets() {
        ConfigurationWidgetParameter vulnerability = new ConfigurationWidgetParameter(VulnerabilityWidget.class, VulnerabilityWidgetListener.class,
                I18NTypes.WORKERS_RUNES_GUI, "vulnerability",
                "vulnerability.male.can.be.killed", "vulnerability.female.can.be.killed",
                "vulnerability.male.cannot.be.killed", "vulnerability.female.cannot.be.killed");
        addViewMapping(GUIElements.GENDER_AND_VULNERABILITY, vulnerability);
    }

    private void addRaceWidgets() {
//        ConfigurationWidgetParameter raceIds = new ConfigurationWidgetParameter(RacesWidget.class, RacesWidgetListener.class, I18NTypes.CREATURES_GUI,
//                "raceId");
//        addViewMapping(GUIElements.RACE_ID, raceIds);
    }

    private void addEquipmentSlotsWidgets() {
        ConfigurationWidgetParameter equipmentSlots = new ConfigurationWidgetParameter(EquipmentSlotWidget.class, EquipmentSlotWidgetListener.class,
                I18NTypes.WORKERS_RUNES_GUI, "equipmentSlotsId", "equipmentSlotsId.all.slots.available",
                "equipmentSlotsId.hands.and.rings.slots.available", "equipmentSlotsId.no.slots.available");
        addViewMapping(GUIElements.EQUIPMENT_SLOTS_ID, equipmentSlots);
    }

    private void addSkillsWidgets() {

    }

    private void addTextFieldWidgets() {
        Map<Integer, String> i18nMap = new HashMap<Integer, String>() {{
            put(GUIElements.STATS_ID, "statsId");
            put(GUIElements.UNIT_LEVEL, "level");
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
            put(GUIElements.RACE_ID, "raceId");
        }};

        for (Map.Entry<Integer, String> entry : i18nMap.entrySet()) {
            ConfigurationWidgetParameter parameter = new ConfigurationWidgetParameter(TextFieldWidget.class, TextFieldWidgetListener.class, I18NTypes.WORKERS_RUNES_GUI, entry.getValue());
            addViewMapping(entry.getKey(), parameter);
        }
    }

    private void addLevelWidget() {
        ConfigurationWidgetParameter level = new ConfigurationWidgetParameter(LevelComboBoxWidget.class, WorkerRunesLevelComboBoxListener.class, I18NTypes.WORKERS_RUNES_GUI,
                "level");
        addViewMapping(GUIElements.LEVEL, level);
    }
}
