package sfgamedataeditor.common.viewconfigurations.item.weapon;

import sfgamedataeditor.common.viewconfigurations.AbstractConfiguration;
import sfgamedataeditor.common.viewconfigurations.ConfigurationWidgetParameter;
import sfgamedataeditor.common.widgets.combobox.requirementclass.RequirementClassSubClassWidget;
import sfgamedataeditor.common.widgets.combobox.requirementclass.RequirementClassSubClassWidgetListener;
import sfgamedataeditor.common.widgets.textfield.TextFieldWidget;
import sfgamedataeditor.common.widgets.textfield.TextFieldWidgetListener;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.util.HashMap;
import java.util.Map;

public class WeaponParametersViewConfiguration extends AbstractConfiguration {
    @Override
    protected void fillConfigurationMappings() {
        Map<Integer, String> i18nMap = new HashMap<Integer, String>() {{
            put(GUIElements.BUY_OUT_PRICE, "buyoutPrice");
            put(GUIElements.SELL_PRICE, "selloutPrice");
            put(GUIElements.ITEM_EFFECT, "effectNumber");
            put(GUIElements.MIN_DAMAGE, "minDamage");
            put(GUIElements.MAX_DAMAGE, "maxDamage");
            put(GUIElements.MIN_RANGE, "minRange");
            put(GUIElements.MAX_RANGE, "maxRange");
            put(GUIElements.SPEED, "speed");
            put(GUIElements.TYPE, "type");
            put(GUIElements.MATERIAL, "material");
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
    }
}
