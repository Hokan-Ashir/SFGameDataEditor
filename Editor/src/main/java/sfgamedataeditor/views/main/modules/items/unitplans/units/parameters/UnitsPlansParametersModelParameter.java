package sfgamedataeditor.views.main.modules.items.unitplans.units.parameters;

import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersObject;
import sfgamedataeditor.mvc.objects.IconableParameter;

import javax.swing.*;

public class UnitsPlansParametersModelParameter extends IconableParameter {
    private final ItemPriceParametersObject priceParametersObject;

    public UnitsPlansParametersModelParameter(ItemPriceParametersObject priceParametersObject, Icon icon) {
        super(icon);
        this.priceParametersObject = priceParametersObject;
    }

    public ItemPriceParametersObject getPriceParametersObject() {
        return priceParametersObject;
    }
}
