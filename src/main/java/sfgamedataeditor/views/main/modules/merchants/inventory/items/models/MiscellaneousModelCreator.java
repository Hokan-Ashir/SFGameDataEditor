package sfgamedataeditor.views.main.modules.merchants.inventory.items.models;

import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersObject;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.views.main.modules.items.miscellaneous.parameters.MiscellaneousParametersModel;
import sfgamedataeditor.views.main.modules.items.miscellaneous.parameters.MiscellaneousParametersModelParameter;

import javax.swing.*;

public class MiscellaneousModelCreator implements ModelCreator<MiscellaneousParametersModel> {

    @Override
    public MiscellaneousParametersModel createModel(int itemId, Icon icon) {
        ItemPriceParametersObject itemPriceObject = ItemPriceParametersTableService.INSTANCE.getObjectByItemId(itemId);
        MiscellaneousParametersModelParameter parameter = new MiscellaneousParametersModelParameter(itemPriceObject);
        return new MiscellaneousParametersModel(parameter);
    }
}
