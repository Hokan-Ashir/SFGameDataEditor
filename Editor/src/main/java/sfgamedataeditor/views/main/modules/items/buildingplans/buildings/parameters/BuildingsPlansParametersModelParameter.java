package sfgamedataeditor.views.main.modules.items.buildingplans.buildings.parameters;

import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersObject;

import javax.swing.*;

public class BuildingsPlansParametersModelParameter {
    private final ItemPriceParametersObject priceParametersObject;
    private final Icon icon;

    public BuildingsPlansParametersModelParameter(ItemPriceParametersObject priceParametersObject, Icon icon) {
        this.priceParametersObject = priceParametersObject;
        this.icon = icon;
    }

    public ItemPriceParametersObject getPriceParametersObject() {
        return priceParametersObject;
    }

    public Icon getIcon() {
        return icon;
    }
}
