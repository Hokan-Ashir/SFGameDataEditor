package sfgamedataeditor.views.main.modules.items.buildingplans.buildings;

import sfgamedataeditor.views.common.presenters.AbstractModulesPresenter;
import sfgamedataeditor.views.main.modules.items.buildingplans.buildings.parameters.BuildingPlansParametersModel;

public class BuildingsPlanListPresenter extends AbstractModulesPresenter<BuildingsPlanListModelParameter, BuildingsPlanListView, BuildingPlansParametersModel> {

    private final BuildingPlansModelCreator modelCreator = new BuildingPlansModelCreator();

    public BuildingsPlanListPresenter(BuildingsPlanListView view) {
        super(view);
    }

    @Override
    protected BuildingPlansParametersModel createModel() {
        Integer itemId = getView().getSelectedModuleObjectId();
        return modelCreator.createModel(itemId);
    }
}
