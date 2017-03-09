package sfgamedataeditor.views.main.modules.items.unitplans.units;

import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersObject;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.views.common.ModelCreator;
import sfgamedataeditor.views.main.modules.items.unitplans.units.parameters.UnitsPlansParametersModel;
import sfgamedataeditor.views.main.modules.items.unitplans.units.parameters.UnitsPlansParametersModelParameter;

import javax.swing.*;

public class UnitPlansModelCreator implements ModelCreator<UnitsPlansParametersModel> {
    @Override
    public UnitsPlansParametersModel createModel(int objectId, Icon icon) {
        ItemPriceParametersObject itemPriceObject = ItemPriceParametersTableService.INSTANCE.getObjectByItemId(objectId);
        UnitsPlansParametersModelParameter parameter = new UnitsPlansParametersModelParameter(itemPriceObject, icon);
        return new UnitsPlansParametersModel(parameter);
    }
}
