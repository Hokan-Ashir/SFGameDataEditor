package sfgamedataeditor.views.main.modules.items.unitplans.units.parameters;

import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersObject;

import javax.swing.*;

public class UnitsPlansParametersModelParameter {
    private final ItemPriceParametersObject priceParametersObject;
    private final Icon icon;

    public UnitsPlansParametersModelParameter(ItemPriceParametersObject priceParametersObject, Icon icon) {
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
