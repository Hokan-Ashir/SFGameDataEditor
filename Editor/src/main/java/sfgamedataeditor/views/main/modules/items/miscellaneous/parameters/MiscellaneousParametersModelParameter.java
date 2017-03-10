package sfgamedataeditor.views.main.modules.items.miscellaneous.parameters;

import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersObject;
import sfgamedataeditor.mvc.objects.IconableParameter;

import javax.swing.*;

public class MiscellaneousParametersModelParameter extends IconableParameter {
    private final ItemPriceParametersObject priceParametersObject;

    public MiscellaneousParametersModelParameter(ItemPriceParametersObject priceParametersObject, Icon icon) {
        super(icon);
        this.priceParametersObject = priceParametersObject;
    }

    public ItemPriceParametersObject getPriceParametersObject() {
        return priceParametersObject;
    }
}
