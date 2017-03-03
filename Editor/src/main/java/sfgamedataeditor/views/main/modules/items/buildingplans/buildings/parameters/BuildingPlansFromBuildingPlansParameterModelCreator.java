package sfgamedataeditor.views.main.modules.items.buildingplans.buildings.parameters;


import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersObject;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.mvc.ModelCreator;
import sfgamedataeditor.views.main.modules.items.buildingplans.buildings.BuildingsPlanListModel;
import sfgamedataeditor.views.main.modules.items.buildingplans.buildings.BuildingsPlanListModelParameter;

import java.util.Set;

public class BuildingPlansFromBuildingPlansParameterModelCreator implements ModelCreator<BuildingsPlanListModel, BuildingPlansParametersModel> {

    @Override
    public BuildingsPlanListModel createModel(BuildingPlansParametersModel childModel) {
        ItemPriceParametersObject object = childModel.getParameter().getPriceParametersObject();
        String selectedBuildingName = object.name;
        Integer buildingRaceId = object.typeId;
        Set<String> buildingsNames = ItemPriceParametersTableService.INSTANCE.getItemsByItemType(buildingRaceId);
        BuildingsPlanListModelParameter parameter = new BuildingsPlanListModelParameter(buildingsNames, selectedBuildingName, buildingRaceId);
        return new BuildingsPlanListModel(parameter);
    }
}
