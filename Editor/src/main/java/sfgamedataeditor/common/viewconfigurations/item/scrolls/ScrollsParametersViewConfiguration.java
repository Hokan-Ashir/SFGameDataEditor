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
                I18NTypes.SCROLLS_AND_SPELLS_GUI, "buyoutPrice", "goldAmount", "silverAmount", "copperAmount");
        addViewMapping(GUIElements.SCROLL_BUY_OUT_PRICE, buyoutPrice);
        addViewMapping(GUIElements.SPELL_BUY_OUT_PRICE, buyoutPrice);

        ConfigurationWidgetParameter selloutPrice = new ConfigurationWidgetParameter(ItemPriceWidget.class, ItemPriceWidgetListener.class,
                I18NTypes.SCROLLS_AND_SPELLS_GUI, "selloutPrice", "goldAmount", "silverAmount", "copperAmount");
        addViewMapping(GUIElements.SCROLL_SELL_PRICE, selloutPrice);
        addViewMapping(GUIElements.SPELL_SELL_PRICE, selloutPrice);

        ConfigurationWidgetParameter itemEffect = new ConfigurationWidgetParameter(EffectNumberWidget.class, EffectNumberWidgetListener.class, I18NTypes.SCROLLS_AND_SPELLS_GUI,
                "effectNumber");
        addViewMapping(GUIElements.SCROLL_ITEM_EFFECT, itemEffect);
        addViewMapping(GUIElements.SPELL_ITEM_EFFECT, itemEffect);
        addViewMapping(GUIElements.ITEM_EFFECT_NUMBER, itemEffect);

        ConfigurationWidgetParameter itemSet = new ConfigurationWidgetParameter(ItemSetWidget.class, ItemSetWidgetListener.class, I18NTypes.SCROLLS_AND_SPELLS_GUI,
                "itemSet");
        addViewMapping(GUIElements.SCROLL_ITEM_SET, itemSet);
        addViewMapping(GUIElements.SPELL_ITEM_SET, itemSet);

        ConfigurationWidgetParameter level = new ConfigurationWidgetParameter(LevelComboBoxWidget.class, ScrollLevelComboBoxListener.class, I18NTypes.SCROLLS_AND_SPELLS_GUI,
                "level");
        addViewMapping(GUIElements.LEVEL, level);
    }
}
