package sfgamedataeditor.views.main.modules.objects.chests.parameters;

import sfgamedataeditor.database.objects.chests.ChestCorpseLootObject;
import sfgamedataeditor.mvc.ModelCreator;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.common.ModulesModel;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

public class ChestsListFromChestsParametersModelCreator implements ModelCreator<ModulesModel,ChestParametersModel> {

    @Override
    public ModulesModel createModel(ChestParametersModel childModel) {
        // TODO maybe it's important to get not first object form list
        ChestCorpseLootObject object = childModel.getParameter().getChestCorpseLootObjects().get(0);
        String chestName = I18NService.INSTANCE.getMessage(I18NTypes.OBJECTS, String.valueOf(object.chestCorpseId));
        ModuleParameter parameter = new ModuleParameter(chestName);
        return new ModulesModel(parameter);
    }
}
