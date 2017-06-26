package sfgamedataeditor.views.main.modules.items.buildingplans.buildings.parameters;


import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersObject;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.database.text.TextTableService;
import sfgamedataeditor.mvc.ModelCreator;
import sfgamedataeditor.views.common.SubViewPanelTuple;
import sfgamedataeditor.views.main.modules.items.buildingplans.buildings.BuildingsPlanListModel;
import sfgamedataeditor.views.main.modules.items.buildingplans.buildings.BuildingsPlanListModelParameter;

import java.util.List;

public class BuildingPlansFromBuildingPlansParameterModelCreator implements ModelCreator<BuildingsPlanListModel, BuildingPlansParametersModel> {

    @Override
    public BuildingsPlanListModel createModel(BuildingPlansParametersModel childModel) {
        ItemPriceParametersObject object = childModel.getParameter().getPriceParametersObject();
        String itemName = TextTableService.INSTANCE.getObjectName(object.nameId);
        Integer buildingRaceId = object.typeId;
        List<SubViewPanelTuple> buildingsNames = ItemPriceParametersTableService.INSTANCE.getItemsByItemType(buildingRaceId);
        BuildingsPlanListModelParameter parameter = new BuildingsPlanListModelParameter(buildingsNames, itemName);
        return new BuildingsPlanListModel(parameter);
    }
}
