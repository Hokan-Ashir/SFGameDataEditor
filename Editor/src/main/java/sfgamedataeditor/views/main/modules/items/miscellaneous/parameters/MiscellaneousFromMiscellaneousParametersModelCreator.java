package sfgamedataeditor.views.main.modules.items.miscellaneous.parameters;

import sfgamedataeditor.mvc.ModelCreator;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.common.ModulesModel;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

public class MiscellaneousFromMiscellaneousParametersModelCreator implements ModelCreator<ModulesModel, MiscellaneousParametersModel> {
    @Override
    public ModulesModel createModel(MiscellaneousParametersModel childModel) {
        Integer itemId = childModel.getParameter().getPriceParametersObject().itemId;
        String selectedItem = I18NService.INSTANCE.getMessage(I18NTypes.ITEMS, String.valueOf(itemId));
        ModuleParameter parameter = new ModuleParameter(selectedItem);
        return new ModulesModel(parameter);
    }
}
