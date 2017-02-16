package sfgamedataeditor.common.viewconfigurations.item.scrolls;

import sfgamedataeditor.common.viewconfigurations.AbstractConfiguration;
import sfgamedataeditor.common.viewconfigurations.ConfigurationWidgetParameter;
import sfgamedataeditor.common.widgets.common.combobox.level.LevelComboBoxWidget;
import sfgamedataeditor.common.widgets.common.combobox.level.ScrollLevelComboBoxListener;
import sfgamedataeditor.common.widgets.common.effectnumber.EffectNumberWidget;
import sfgamedataeditor.common.widgets.common.effectnumber.EffectNumberWidgetListener;
import sfgamedataeditor.common.widgets.items.itemprice.ItemPriceWidget;
import sfgamedataeditor.common.widgets.items.itemprice.ItemPriceWidgetListener;
import sfgamedataeditor.common.widgets.items.itemset.ItemSetWidget;
import sfgamedataeditor.common.widgets.items.itemset.ItemSetWidgetListener;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

public class ScrollsParametersViewConfiguration extends AbstractConfiguration {
    @Override
    protected void fillConfigurationMappings() {
        ConfigurationWidgetParameter buyoutPrice = new ConfigurationWidgetParameter(ItemPriceWidget.class, ItemPriceWidgetListener.class,
                I18NTypes.WEAPON_GUI, "buyoutPrice", "goldAmount", "silverAmount", "copperAmount");
        addViewMapping(GUIElements.BUY_OUT_PRICE, buyoutPrice);

        ConfigurationWidgetParameter selloutPrice = new ConfigurationWidgetParameter(ItemPriceWidget.class, ItemPriceWidgetListener.class,
                I18NTypes.WEAPON_GUI, "selloutPrice", "goldAmount", "silverAmount", "copperAmount");
        addViewMapping(GUIElements.SELL_PRICE, selloutPrice);

        ConfigurationWidgetParameter itemEffect = new ConfigurationWidgetParameter(EffectNumberWidget.class, EffectNumberWidgetListener.class, I18NTypes.WEAPON_GUI,
                "effectNumber");
        addViewMapping(GUIElements.ITEM_EFFECT, itemEffect);

        ConfigurationWidgetParameter itemSet = new ConfigurationWidgetParameter(ItemSetWidget.class, ItemSetWidgetListener.class, I18NTypes.WEAPON_GUI,
                "itemSet");
        addViewMapping(GUIElements.ITEM_SET, itemSet);

        ConfigurationWidgetParameter level = new ConfigurationWidgetParameter(LevelComboBoxWidget.class, ScrollLevelComboBoxListener.class, I18NTypes.WEAPON_GUI,
                "level");
        addViewMapping(GUIElements.LEVEL, level);
    }
}
