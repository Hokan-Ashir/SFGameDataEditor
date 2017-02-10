package sfgamedataeditor.common.viewconfigurations.item.miscellaneous;

import sfgamedataeditor.common.viewconfigurations.AbstractConfiguration;
import sfgamedataeditor.common.viewconfigurations.ConfigurationWidgetParameter;
import sfgamedataeditor.common.widgets.common.textfield.TextFieldWidget;
import sfgamedataeditor.common.widgets.common.textfield.TextFieldWidgetListener;
import sfgamedataeditor.common.widgets.items.itemset.ItemSetWidget;
import sfgamedataeditor.common.widgets.items.itemset.ItemSetWidgetListener;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.util.HashMap;
import java.util.Map;

public class MiscellaneousParametersViewConfiguration extends AbstractConfiguration {
    @Override
    protected void fillConfigurationMappings() {
        Map<Integer, String> i18nMap = new HashMap<Integer, String>() {{
            put(GUIElements.BUY_OUT_PRICE, "buyoutPrice");
            put(GUIElements.SELL_PRICE, "selloutPrice");
        }};

        for (Map.Entry<Integer, String> entry : i18nMap.entrySet()) {
            ConfigurationWidgetParameter parameter = new ConfigurationWidgetParameter(TextFieldWidget.class, TextFieldWidgetListener.class, I18NTypes.ARMOR_GUI, entry.getValue());
            addViewMapping(entry.getKey(), parameter);
        }

        ConfigurationWidgetParameter itemSet = new ConfigurationWidgetParameter(ItemSetWidget.class, ItemSetWidgetListener.class, I18NTypes.WEAPON_GUI,
                "itemSet");
        addViewMapping(GUIElements.ITEM_SET, itemSet);
    }
}
