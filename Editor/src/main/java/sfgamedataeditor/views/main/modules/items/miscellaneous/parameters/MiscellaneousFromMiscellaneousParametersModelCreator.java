package sfgamedataeditor.views.main.modules.items.miscellaneous.parameters;

import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersObject;
import sfgamedataeditor.database.text.TextTableService;
import sfgamedataeditor.mvc.ModelCreator;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.common.ModulesModel;
import sfgamedataeditor.views.common.ObjectTuple;

public class MiscellaneousFromMiscellaneousParametersModelCreator implements ModelCreator<ModulesModel, MiscellaneousParametersModel> {
    @Override
    public ModulesModel createModel(MiscellaneousParametersModel childModel) {
        ItemPriceParametersObject object = childModel.getParameter().getPriceParametersObject();
        ObjectTuple selectedItem = TextTableService.INSTANCE.getObjectTuple(object.nameId, object.itemId);
        ModuleParameter parameter = new ModuleParameter(selectedItem);
        return new ModulesModel(parameter);
    }
}
