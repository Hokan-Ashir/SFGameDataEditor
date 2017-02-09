package sfgamedataeditor.common.viewconfigurations.item.armor;

import sfgamedataeditor.common.viewconfigurations.AbstractConfiguration;
import sfgamedataeditor.common.viewconfigurations.ConfigurationWidgetParameter;
import sfgamedataeditor.common.widgets.common.combobox.requirementclass.RequirementClassSubClassWidget;
import sfgamedataeditor.common.widgets.common.combobox.requirementclass.RequirementClassSubClassWidgetListener;
import sfgamedataeditor.common.widgets.common.textfield.TextFieldWidget;
import sfgamedataeditor.common.widgets.common.textfield.TextFieldWidgetListener;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.util.HashMap;
import java.util.Map;

public class ArmorParametersViewConfiguration extends AbstractConfiguration {
    @Override
    protected void fillConfigurationMappings() {
        Map<Integer, String> i18nMap = new HashMap<Integer, String>() {{
            put(GUIElements.BUY_OUT_PRICE, "buyoutPrice");
            put(GUIElements.SELL_PRICE, "selloutPrice");
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
            put(GUIElements.WALK_SPEED, "walkSpeed");
            put(GUIElements.FIGHT_SPEED, "fightSpeed");
            put(GUIElements.CAST_SPEED, "castSpeed");
            put(GUIElements.REQUIREMENT_LEVEL, "requirementLevel");
            put(GUIElements.ITEM_SET_PANEL, "itemSet");
        }};

        for (Map.Entry<Integer, String> entry : i18nMap.entrySet()) {
            ConfigurationWidgetParameter parameter = new ConfigurationWidgetParameter(TextFieldWidget.class, TextFieldWidgetListener.class, I18NTypes.ARMOR_GUI, entry.getValue());
            addViewMapping(entry.getKey(), parameter);
        }

        ConfigurationWidgetParameter requirementClassSubClass = new ConfigurationWidgetParameter(RequirementClassSubClassWidget.class,
                RequirementClassSubClassWidgetListener.class, I18NTypes.ARMOR_GUI,
                "requirementClass", "requirementSubClass");
        addViewMapping(GUIElements.REQUIREMENT_CLASS_SUBCLASS, requirementClassSubClass);
    }
}
