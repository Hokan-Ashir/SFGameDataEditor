package sfgamedataeditor.views.main.modules.items.miscellaneous.parameters;

import sfgamedataeditor.database.text.TextTableService;
import sfgamedataeditor.mvc.ModelCreator;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.common.ModulesModel;
import sfgamedataeditor.views.common.ObjectTuple;

public class MiscellaneousFromMiscellaneousParametersModelCreator implements ModelCreator<ModulesModel, MiscellaneousParametersModel> {
    @Override
    public ModulesModel createModel(MiscellaneousParametersModel childModel) {
        Integer nameId = childModel.getParameter().getPriceParametersObject().nameId;
        ObjectTuple selectedItem = TextTableService.INSTANCE.getObjectTuple(nameId, nameId);
        ModuleParameter parameter = new ModuleParameter(selectedItem);
        return new ModulesModel(parameter);
    }
}
