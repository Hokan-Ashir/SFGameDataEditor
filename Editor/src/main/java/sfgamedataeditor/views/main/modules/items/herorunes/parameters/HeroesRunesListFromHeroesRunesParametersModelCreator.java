package sfgamedataeditor.views.main.modules.items.herorunes.parameters;


import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersObject;
import sfgamedataeditor.database.text.TextTableService;
import sfgamedataeditor.mvc.ModelCreator;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.common.ModulesModel;
import sfgamedataeditor.views.common.ObjectTuple;

public class HeroesRunesListFromHeroesRunesParametersModelCreator implements ModelCreator<ModulesModel, HeroesRunesParametersModel> {

    @Override
    public ModulesModel createModel(HeroesRunesParametersModel childModel) {
        ItemPriceParametersObject object = childModel.getParameter().getPriceParametersObject();
        ObjectTuple itemName = TextTableService.INSTANCE.getObjectTuple(object.nameId, object.itemId);
        ModuleParameter parameter = new ModuleParameter(itemName);
        return new ModulesModel(parameter);
    }
}
