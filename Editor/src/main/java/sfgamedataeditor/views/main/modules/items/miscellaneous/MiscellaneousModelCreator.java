package sfgamedataeditor.views.main.modules.items.miscellaneous;

import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersObject;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.views.common.ModelCreator;
import sfgamedataeditor.views.main.modules.items.miscellaneous.parameters.MiscellaneousParametersModel;
import sfgamedataeditor.views.main.modules.items.miscellaneous.parameters.MiscellaneousParametersModelParameter;

import javax.swing.*;

public class MiscellaneousModelCreator implements ModelCreator<MiscellaneousParametersModel> {

    @Override
    public MiscellaneousParametersModel createModel(int objectId, Icon icon) {
        ItemPriceParametersObject itemPriceObject = ItemPriceParametersTableService.INSTANCE.getObjectByItemId(objectId);
        MiscellaneousParametersModelParameter parameter = new MiscellaneousParametersModelParameter(itemPriceObject, icon);
        return new MiscellaneousParametersModel(parameter);
    }
}
