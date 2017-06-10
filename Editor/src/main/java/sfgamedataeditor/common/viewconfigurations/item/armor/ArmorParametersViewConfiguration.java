package sfgamedataeditor.common.viewconfigurations.item.armor;

import sfgamedataeditor.common.viewconfigurations.AbstractConfiguration;
import sfgamedataeditor.common.viewconfigurations.ConfigurationWidgetParameter;
import sfgamedataeditor.common.widgets.common.combobox.requirementclass.RequirementClassSubClassWidget;
import sfgamedataeditor.common.widgets.common.combobox.requirementclass.SpellRequirementClassSubClassWidgetListener;
import sfgamedataeditor.common.widgets.common.textfield.TextFieldWidget;
import sfgamedataeditor.common.widgets.common.textfield.TextFieldWidgetListener;
import sfgamedataeditor.common.widgets.items.itemprice.ItemPriceWidget;
import sfgamedataeditor.common.widgets.items.itemprice.ItemPriceWidgetListener;
import sfgamedataeditor.common.widgets.items.itemset.ItemSetWidget;
import sfgamedataeditor.common.widgets.items.itemset.ItemSetWidgetListener;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.util.HashMap;
import java.util.Map;

public class ArmorParametersViewConfiguration extends AbstractConfiguration {
    @Override
    protected void fillConfigurationMappings() {
        Map<Integer, String> i18nMap = new HashMap<Integer, String>() {{
            put(GUIElements.STRENGTH, "strength");
            put(GUIElements.STAMINA, "stamina");
            put(GUIElements.AGILITY, "agility");
            put(GUIElements.DEXTERITY, "dexterity");
            put(GUIElements.WISDOM, "wisdom");
            put(GUIElements.INTELLIGENCE, "intelligence");
            put(GUIElements.CHARISMA, "charisma");
            put(GUIElements.MANA, "mana");
            put(GUIElements.ARMOR, "armor");
            put(GUIElements.FIRE_RESISTANCE, "fireResistance");
            put(GUIElements.ICE_RESISTANCE, "iceResistance");
            put(GUIElements.MIND_RESISTANCE, "mindResistance");
            put(GUIElements.BLACK_RESISTANCE, "blackResistance");
            put(GUIElements.RUN_SPEED, "walkSpeed");
            put(GUIElements.FIGHT_SPEED, "fightSpeed");
            put(GUIElements.CAST_SPEED, "castSpeed");
            put(GUIElements.REQUIREMENT_LEVEL, "requirementLevel");
        }};

        for (Map.Entry<Integer, String> entry : i18nMap.entrySet()) {
            ConfigurationWidgetParameter parameter = new ConfigurationWidgetParameter(TextFieldWidget.class, TextFieldWidgetListener.class, I18NTypes.ARMOR_GUI, entry.getValue());
            addViewMapping(entry.getKey(), parameter);
        }

        ConfigurationWidgetParameter requirementClassSubClass = new ConfigurationWidgetParameter(RequirementClassSubClassWidget.class,
                SpellRequirementClassSubClassWidgetListener.class, I18NTypes.ARMOR_GUI,
                "requirementClass", "requirementSubClass");
        addViewMapping(GUIElements.REQUIREMENT_CLASS_SUBCLASS, requirementClassSubClass);

        ConfigurationWidgetParameter itemSet = new ConfigurationWidgetParameter(ItemSetWidget.class,
                ItemSetWidgetListener.class, I18NTypes.ARMOR_GUI,
                "itemSet");
        addViewMapping(GUIElements.ITEM_SET_PANEL, itemSet);

        ConfigurationWidgetParameter buyoutPrice = new ConfigurationWidgetParameter(ItemPriceWidget.class, ItemPriceWidgetListener.class,
                I18NTypes.ARMOR_GUI, "buyoutPrice", "goldAmount", "silverAmount", "copperAmount");
        addViewMapping(GUIElements.BUY_OUT_PRICE, buyoutPrice);

        ConfigurationWidgetParameter selloutPrice = new ConfigurationWidgetParameter(ItemPriceWidget.class, ItemPriceWidgetListener.class,
                I18NTypes.ARMOR_GUI, "selloutPrice", "goldAmount", "silverAmount", "copperAmount");
        addViewMapping(GUIElements.SELL_PRICE, selloutPrice);
    }
}
