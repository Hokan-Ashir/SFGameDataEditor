package sfgamedataeditor.views.main.modules.items.buildingplans.buildings;

import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersObject;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.views.common.ModelCreator;
import sfgamedataeditor.views.main.modules.items.buildingplans.buildings.parameters.BuildingPlansParametersModel;
import sfgamedataeditor.views.main.modules.items.buildingplans.buildings.parameters.BuildingsPlansParametersModelParameter;

import javax.swing.*;

public class BuildingPlansModelCreator implements ModelCreator<BuildingPlansParametersModel> {
    @Override
    public BuildingPlansParametersModel createModel(int objectId, Icon icon) {
        ItemPriceParametersObject itemPriceObject = ItemPriceParametersTableService.INSTANCE.getObjectByItemId(objectId);
        BuildingsPlansParametersModelParameter parameter = new BuildingsPlansParametersModelParameter(itemPriceObject, icon);
        return new BuildingPlansParametersModel(parameter);
    }
}
