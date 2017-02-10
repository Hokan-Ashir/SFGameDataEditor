package sfgamedataeditor.common.viewconfigurations.item.weapon;

import sfgamedataeditor.common.viewconfigurations.AbstractConfiguration;
import sfgamedataeditor.common.viewconfigurations.ConfigurationWidgetParameter;
import sfgamedataeditor.common.widgets.common.combobox.requirementclass.RequirementClassSubClassWidget;
import sfgamedataeditor.common.widgets.common.combobox.requirementclass.RequirementClassSubClassWidgetListener;
import sfgamedataeditor.common.widgets.common.effectnumber.EffectNumberWidget;
import sfgamedataeditor.common.widgets.common.effectnumber.EffectNumberWidgetListener;
import sfgamedataeditor.common.widgets.common.textfield.TextFieldWidget;
import sfgamedataeditor.common.widgets.common.textfield.TextFieldWidgetListener;
import sfgamedataeditor.common.widgets.items.itemprice.ItemPriceWidget;
import sfgamedataeditor.common.widgets.items.itemprice.ItemPriceWidgetListener;
import sfgamedataeditor.common.widgets.items.itemset.ItemSetWidget;
import sfgamedataeditor.common.widgets.items.itemset.ItemSetWidgetListener;
import sfgamedataeditor.common.widgets.items.weapons.metrial.WeaponMaterialWidget;
import sfgamedataeditor.common.widgets.items.weapons.metrial.WeaponMaterialWidgetListener;
import sfgamedataeditor.common.widgets.items.weapons.type.WeaponTypeWidget;
import sfgamedataeditor.common.widgets.items.weapons.type.WeaponTypeWidgetListener;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.util.HashMap;
import java.util.Map;

public class WeaponParametersViewConfiguration extends AbstractConfiguration {
    @Override
    protected void fillConfigurationMappings() {
        Map<Integer, String> i18nMap = new HashMap<Integer, String>() {{
            put(GUIElements.MIN_DAMAGE, "minDamage");
            put(GUIElements.MAX_DAMAGE, "maxDamage");
            put(GUIElements.MIN_RANGE, "minRange");
            put(GUIElements.MAX_RANGE, "maxRange");
            put(GUIElements.SPEED, "speed");
            put(GUIElements.REQUIREMENT_LEVEL, "requirementLevel");
        }};

        for (Map.Entry<Integer, String> entry : i18nMap.entrySet()) {
            ConfigurationWidgetParameter parameter = new ConfigurationWidgetParameter(TextFieldWidget.class, TextFieldWidgetListener.class, I18NTypes.WEAPON_GUI, entry.getValue());
            addViewMapping(entry.getKey(), parameter);
        }

        ConfigurationWidgetParameter requirementClassSubClass = new ConfigurationWidgetParameter(RequirementClassSubClassWidget.class,
                RequirementClassSubClassWidgetListener.class, I18NTypes.WEAPON_GUI,
                "requirementClass", "requirementSubClass");
        addViewMapping(GUIElements.REQUIREMENT_CLASS_SUBCLASS, requirementClassSubClass);

        ConfigurationWidgetParameter itemEffect = new ConfigurationWidgetParameter(EffectNumberWidget.class, EffectNumberWidgetListener.class, I18NTypes.WEAPON_GUI,
                "effectNumber");
        addViewMapping(GUIElements.ITEM_EFFECT, itemEffect);

        ConfigurationWidgetParameter material = new ConfigurationWidgetParameter(WeaponMaterialWidget.class, WeaponMaterialWidgetListener.class, I18NTypes.WEAPON_GUI,
                "material");
        addViewMapping(GUIElements.MATERIAL, material);

        ConfigurationWidgetParameter type = new ConfigurationWidgetParameter(WeaponTypeWidget.class, WeaponTypeWidgetListener.class, I18NTypes.WEAPON_GUI,
                "type");
        addViewMapping(GUIElements.TYPE, type);

        ConfigurationWidgetParameter itemSet = new ConfigurationWidgetParameter(ItemSetWidget.class, ItemSetWidgetListener.class, I18NTypes.WEAPON_GUI,
                "itemSet");
        addViewMapping(GUIElements.ITEM_SET, itemSet);

        ConfigurationWidgetParameter buyoutPrice = new ConfigurationWidgetParameter(ItemPriceWidget.class, ItemPriceWidgetListener.class,
                I18NTypes.WEAPON_GUI, "buyoutPrice", "goldAmount", "silverAmount", "copperAmount");
        addViewMapping(GUIElements.BUY_OUT_PRICE, buyoutPrice);

        ConfigurationWidgetParameter selloutPrice = new ConfigurationWidgetParameter(ItemPriceWidget.class, ItemPriceWidgetListener.class,
                I18NTypes.WEAPON_GUI, "selloutPrice", "goldAmount", "silverAmount", "copperAmount");
        addViewMapping(GUIElements.SELL_PRICE, selloutPrice);
    }
}
