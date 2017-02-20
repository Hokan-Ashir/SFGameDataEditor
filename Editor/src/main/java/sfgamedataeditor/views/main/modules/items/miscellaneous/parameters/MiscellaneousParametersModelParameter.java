package sfgamedataeditor.views.main.modules.items.miscellaneous.parameters;

import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersObject;

public class MiscellaneousParametersModelParameter {
    private final ItemPriceParametersObject priceParametersObject;

    public MiscellaneousParametersModelParameter(ItemPriceParametersObject priceParametersObject) {
        this.priceParametersObject = priceParametersObject;
    }

    public ItemPriceParametersObject getPriceParametersObject() {
        return priceParametersObject;
    }
}
