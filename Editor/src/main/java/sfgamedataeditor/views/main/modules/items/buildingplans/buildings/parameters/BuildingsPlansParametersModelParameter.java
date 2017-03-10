package sfgamedataeditor.views.main.modules.items.buildingplans.buildings.parameters;

import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersObject;
import sfgamedataeditor.mvc.objects.IconableParameter;

import javax.swing.*;

public class BuildingsPlansParametersModelParameter extends IconableParameter {
    private final ItemPriceParametersObject priceParametersObject;

    public BuildingsPlansParametersModelParameter(ItemPriceParametersObject priceParametersObject, Icon icon) {
        super(icon);
        this.priceParametersObject = priceParametersObject;
    }

    public ItemPriceParametersObject getPriceParametersObject() {
        return priceParametersObject;
    }
}
