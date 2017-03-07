package sfgamedataeditor.views.main.modules.items.herorunes.parameters;


import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersObject;
import sfgamedataeditor.mvc.ModelCreator;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.common.ModulesModel;

public class HeroesRunesListFromHeroesRunesParametersModelCreator implements ModelCreator<ModulesModel, HeroesRunesParametersModel> {

    @Override
    public ModulesModel createModel(HeroesRunesParametersModel childModel) {
        ItemPriceParametersObject priceParametersObject = childModel.getParameter().getPriceParametersObject();
        ModuleParameter parameter = new ModuleParameter(priceParametersObject.name);
        return new ModulesModel(parameter);
    }
}
