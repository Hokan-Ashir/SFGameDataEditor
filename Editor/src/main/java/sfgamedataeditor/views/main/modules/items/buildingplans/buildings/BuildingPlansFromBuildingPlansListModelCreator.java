package sfgamedataeditor.views.main.modules.items.buildingplans.buildings;


import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersObject;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.mvc.ModelCreator;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.common.ModulesModel;
import sfgamedataeditor.views.common.ObjectTuple;
import sfgamedataeditor.views.main.modules.items.buildingplans.BuildingRaceToItemPlansMapping;

public class BuildingPlansFromBuildingPlansListModelCreator implements ModelCreator<ModulesModel, BuildingsPlanListModel> {

    @Override
    public ModulesModel createModel(BuildingsPlanListModel childModel) {
        ObjectTuple itemName = childModel.getParameter().getSelectedModuleName();
        String raceName = null;
        Integer raceId = null;
        if (itemName != null) {
            Integer itemId = ItemPriceParametersTableService.INSTANCE.getItemIdByItemNameAndType(itemName.getName(), BuildingRaceToItemPlansMapping.INSTANCE.getTypes());
            ItemPriceParametersObject object = ItemPriceParametersTableService.INSTANCE.getObjectByItemId(itemId);
            raceId = object.typeId;
            raceName = BuildingRaceToItemPlansMapping.INSTANCE.getRaceNameByItemType(raceId);
        }

        ModuleParameter parameter = new ModuleParameter(new ObjectTuple(raceName, raceId));
        return new ModulesModel(parameter);
    }
}
