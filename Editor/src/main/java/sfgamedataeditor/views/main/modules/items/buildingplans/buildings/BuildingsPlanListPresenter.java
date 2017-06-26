package sfgamedataeditor.views.main.modules.items.buildingplans.buildings;

import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.views.common.presenters.AbstractModulesPresenter;
import sfgamedataeditor.views.main.modules.items.buildingplans.buildings.parameters.BuildingPlansParametersModel;

import javax.swing.*;

public class BuildingsPlanListPresenter extends AbstractModulesPresenter<BuildingsPlanListModelParameter, BuildingsPlanListView, BuildingPlansParametersModel> {

    private final BuildingPlansModelCreator modelCreator = new BuildingPlansModelCreator();

    public BuildingsPlanListPresenter(BuildingsPlanListView view) {
        super(view);
    }

    @Override
    protected BuildingPlansParametersModel createModel() {
        Integer itemId = getView().getSelectedModuleObjectId();
        Icon icon = getView().getSelectedModuleIcon();
        return modelCreator.createModel(itemId, icon);
    }
}
