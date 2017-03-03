package sfgamedataeditor.views.main.modules.items.buildingplans.buildings;

import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersObject;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.views.common.AbstractModulesPresenter;
import sfgamedataeditor.views.main.modules.items.buildingplans.buildings.parameters.BuildingPlansParametersModel;
import sfgamedataeditor.views.main.modules.items.buildingplans.buildings.parameters.BuildingsPlansParametersModelParameter;

import javax.swing.*;

public class BuildingsPlanListPresenter extends AbstractModulesPresenter<BuildingsPlanListModelParameter, BuildingsPlanListView, BuildingPlansParametersModel> {

    public BuildingsPlanListPresenter(BuildingsPlanListView view) {
        super(view);
    }

    @Override
    protected BuildingPlansParametersModel createModel() {
        String selectedBuildingName = getView().getSelectedModuleName();
        Integer buildingsRaceType = getModel().getParameter().getBuildingsRaceType();
        int itemId = ItemPriceParametersTableService.INSTANCE.getItemIdByItemNameAndType(selectedBuildingName, buildingsRaceType);
        Icon icon = getView().getSelectedModuleIcon();
        ItemPriceParametersObject itemPriceObject = ItemPriceParametersTableService.INSTANCE.getObjectByItemId(itemId);
        BuildingsPlansParametersModelParameter parameter = new BuildingsPlansParametersModelParameter(itemPriceObject, icon);
        return new BuildingPlansParametersModel(parameter);
    }
}
