package sfgamedataeditor.views.main.modules.items.workersrunes;

import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersObject;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.database.text.TextTableService;
import sfgamedataeditor.views.common.ObjectTuple;
import sfgamedataeditor.views.common.model.creators.ModelCreator;
import sfgamedataeditor.views.main.modules.items.workersrunes.parameters.WorkersRunesParametersModel;
import sfgamedataeditor.views.main.modules.items.workersrunes.parameters.WorkersRunesParametersModelParameter;

import javax.swing.*;

public class WorkerRunesModelCreator implements ModelCreator<WorkersRunesParametersModel> {
    @Override
    public WorkersRunesParametersModel createModel(int objectId, Icon icon) {
        ItemPriceParametersObject object = ItemPriceParametersTableService.INSTANCE.getObjectByItemId(objectId);
        ObjectTuple itemName = TextTableService.INSTANCE.getObjectTuple(object.nameId, objectId);
        WorkersRunesParametersModelParameter parameter = new WorkersRunesParametersModelParameter(itemName.getName(), 1, icon);
        return new WorkersRunesParametersModel(parameter);
    }
}
