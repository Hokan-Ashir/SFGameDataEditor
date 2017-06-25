package sfgamedataeditor.views.main.modules.items.herorunes.parameters;


import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersObject;
import sfgamedataeditor.database.text.TextTableService;
import sfgamedataeditor.mvc.ModelCreator;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.common.ModulesModel;

public class HeroesRunesListFromHeroesRunesParametersModelCreator implements ModelCreator<ModulesModel, HeroesRunesParametersModel> {

    @Override
    public ModulesModel createModel(HeroesRunesParametersModel childModel) {
        ItemPriceParametersObject object = childModel.getParameter().getPriceParametersObject();
        String itemName = TextTableService.INSTANCE.getObjectName(object.nameId);
        ModuleParameter parameter = new ModuleParameter(itemName);
        return new ModulesModel(parameter);
    }
}
