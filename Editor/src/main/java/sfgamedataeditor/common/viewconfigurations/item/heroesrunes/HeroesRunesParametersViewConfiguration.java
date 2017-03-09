package sfgamedataeditor.common.viewconfigurations.item.heroesrunes;

import sfgamedataeditor.common.viewconfigurations.AbstractConfiguration;
import sfgamedataeditor.common.viewconfigurations.ConfigurationWidgetParameter;
import sfgamedataeditor.common.widgets.common.combobox.requirementclass.RequirementClassSubClassWidget;
import sfgamedataeditor.common.widgets.common.combobox.requirementclass.SpellRequirementClassSubClassWidgetListener;
import sfgamedataeditor.common.widgets.common.effectnumber.EffectNumberWidget;
import sfgamedataeditor.common.widgets.common.effectnumber.EffectNumberWidgetListener;
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
import sfgamedataeditor.common.widgets.items.runerace.RuneRaceWidget;
import sfgamedataeditor.common.widgets.items.runerace.RuneRaceWidgetListener;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.util.HashMap;
import java.util.Map;

public class HeroesRunesParametersViewConfiguration extends AbstractConfiguration {
    @Override
    protected void fillConfigurationMappings() {
        addTextFieldWidgets();
        addVulnerabilityWidgets();
        addSkillsWidgets();
        addItemsWidgets();
        addEquipmentSlotsWidgets();
        addSpellsWidgets();
        addRaceWidgets();
    }

    private void addSpellsWidgets() {
        ConfigurationWidgetParameter spells = new ConfigurationWidgetParameter(EffectNumberWidget.class, EffectNumberWidgetListener.class,
                I18NTypes.WORKERS_RUNES_GUI, "spell");
        addViewMapping(GUIElements.SPELL_PANEL_1, spells);
        addViewMapping(GUIElements.SPELL_PANEL_2, spells);
        addViewMapping(GUIElements.SPELL_PANEL_3, spells);
        addViewMapping(GUIElements.SPELL_PANEL_4, spells);
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

    private void addRaceWidgets() {
        ConfigurationWidgetParameter raceIds = new ConfigurationWidgetParameter(RuneRaceWidget.class, RuneRaceWidgetListener.class, I18NTypes.CREATURES_GUI,
                "raceId");
        addViewMapping(GUIElements.RACE_ID, raceIds);
    }

    private void addVulnerabilityWidgets() {
        ConfigurationWidgetParameter vulnerability = new ConfigurationWidgetParameter(VulnerabilityWidget.class, VulnerabilityWidgetListener.class,
                I18NTypes.WORKERS_RUNES_GUI, "vulnerability",
                "vulnerability.male.can.be.killed", "vulnerability.female.can.be.killed",
                "vulnerability.male.cannot.be.killed", "vulnerability.female.cannot.be.killed");
        addViewMapping(GUIElements.GENDER_AND_VULNERABILITY, vulnerability);
    }

    private void addEquipmentSlotsWidgets() {
        ConfigurationWidgetParameter equipmentSlots = new ConfigurationWidgetParameter(EquipmentSlotWidget.class, EquipmentSlotWidgetListener.class,
                I18NTypes.WORKERS_RUNES_GUI, "equipmentSlotsId", "equipmentSlotsId.all.slots.available",
                "equipmentSlotsId.hands.and.rings.slots.available", "equipmentSlotsId.no.slots.available");
        addViewMapping(GUIElements.EQUIPMENT_SLOTS_ID, equipmentSlots);
    }

    private void addSkillsWidgets() {
        ConfigurationWidgetParameter requirementClassSubClass = new ConfigurationWidgetParameter(RequirementClassSubClassWidget.class,
                SpellRequirementClassSubClassWidgetListener.class, I18NTypes.WORKERS_RUNES_GUI,
                "skill.class", "skill.subclass");
        addViewMapping(GUIElements.REQUIREMENT_CLASS_SUBCLASS_1, requirementClassSubClass);
        addViewMapping(GUIElements.REQUIREMENT_CLASS_SUBCLASS_2, requirementClassSubClass);
        addViewMapping(GUIElements.REQUIREMENT_CLASS_SUBCLASS_3, requirementClassSubClass);
        addViewMapping(GUIElements.REQUIREMENT_CLASS_SUBCLASS_4, requirementClassSubClass);
        addViewMapping(GUIElements.REQUIREMENT_CLASS_SUBCLASS_5, requirementClassSubClass);
        addViewMapping(GUIElements.REQUIREMENT_CLASS_SUBCLASS_6, requirementClassSubClass);
        addViewMapping(GUIElements.REQUIREMENT_CLASS_SUBCLASS_7, requirementClassSubClass);
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

            put(GUIElements.SKILL_LEVEL_1, "level");
            put(GUIElements.SKILL_LEVEL_2, "level");
            put(GUIElements.SKILL_LEVEL_3, "level");
            put(GUIElements.SKILL_LEVEL_4, "level");
            put(GUIElements.SKILL_LEVEL_5, "level");
            put(GUIElements.SKILL_LEVEL_6, "level");
            put(GUIElements.SKILL_LEVEL_7, "level");
        }};

        for (Map.Entry<Integer, String> entry : i18nMap.entrySet()) {
            ConfigurationWidgetParameter parameter = new ConfigurationWidgetParameter(TextFieldWidget.class, TextFieldWidgetListener.class, I18NTypes.WORKERS_RUNES_GUI, entry.getValue());
            addViewMapping(entry.getKey(), parameter);
        }
    }
}
