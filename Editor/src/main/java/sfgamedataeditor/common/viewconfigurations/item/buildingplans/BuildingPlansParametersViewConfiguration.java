package sfgamedataeditor.common.viewconfigurations.item.buildingplans;

import sfgamedataeditor.common.viewconfigurations.AbstractConfiguration;
import sfgamedataeditor.common.viewconfigurations.ConfigurationWidgetParameter;
import sfgamedataeditor.common.widgets.items.itemprice.ItemPriceWidget;
import sfgamedataeditor.common.widgets.items.itemprice.ItemPriceWidgetListener;
import sfgamedataeditor.common.widgets.items.itemset.ItemSetWidget;
import sfgamedataeditor.common.widgets.items.itemset.ItemSetWidgetListener;
import sfgamedataeditor.common.widgets.units.buildings.BuildingsWidget;
import sfgamedataeditor.common.widgets.units.buildings.BuildingsWidgetListener;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

public class BuildingPlansParametersViewConfiguration extends AbstractConfiguration {
    @Override
    protected void fillConfigurationMappings() {
        ConfigurationWidgetParameter buyoutPrice = new ConfigurationWidgetParameter(ItemPriceWidget.class, ItemPriceWidgetListener.class,
                I18NTypes.ARMOR_GUI, "buyoutPrice", "goldAmount", "silverAmount", "copperAmount");
        addViewMapping(GUIElements.BUY_OUT_PRICE, buyoutPrice);

        ConfigurationWidgetParameter selloutPrice = new ConfigurationWidgetParameter(ItemPriceWidget.class, ItemPriceWidgetListener.class,
                I18NTypes.ARMOR_GUI, "selloutPrice", "goldAmount", "silverAmount", "copperAmount");
        addViewMapping(GUIElements.SELL_PRICE, selloutPrice);

        ConfigurationWidgetParameter itemSet = new ConfigurationWidgetParameter(ItemSetWidget.class, ItemSetWidgetListener.class, I18NTypes.WEAPON_GUI,
                "itemSet");
        addViewMapping(GUIElements.ITEM_SET, itemSet);

        ConfigurationWidgetParameter building = new ConfigurationWidgetParameter(BuildingsWidget.class, BuildingsWidgetListener.class, I18NTypes.UNITS_GUI, "building");
        addViewMapping(GUIElements.BUILDING, building);
    }
}
