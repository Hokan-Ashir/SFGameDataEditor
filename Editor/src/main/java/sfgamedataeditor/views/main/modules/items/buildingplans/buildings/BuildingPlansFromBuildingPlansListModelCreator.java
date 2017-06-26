package sfgamedataeditor.views.main.modules.items.buildingplans.buildings;


import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersObject;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.mvc.ModelCreator;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.common.ModulesModel;
import sfgamedataeditor.views.main.modules.items.buildingplans.BuildingRaceToItemPlansMapping;
import sfgamedataeditor.views.main.modules.items.unitplans.UnitRaceToItemPlansMapping;

public class BuildingPlansFromBuildingPlansListModelCreator implements ModelCreator<ModulesModel, BuildingsPlanListModel> {

    @Override
    public ModulesModel createModel(BuildingsPlanListModel childModel) {
        String itemName = childModel.getParameter().getSelectedModuleName();
        String raceName = null;
        if (itemName != null) {
            Integer itemId = ItemPriceParametersTableService.INSTANCE.getItemIdByItemNameAndType(itemName, BuildingRaceToItemPlansMapping.INSTANCE.getTypes());
            ItemPriceParametersObject object = ItemPriceParametersTableService.INSTANCE.getObjectByItemId(itemId);
            raceName = BuildingRaceToItemPlansMapping.INSTANCE.getRaceNameByItemType(object.typeId);
        }

        ModuleParameter parameter = new ModuleParameter(raceName);
        return new ModulesModel(parameter);
    }
}
