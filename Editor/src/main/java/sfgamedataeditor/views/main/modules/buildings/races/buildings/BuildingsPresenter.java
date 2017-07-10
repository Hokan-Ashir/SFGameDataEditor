package sfgamedataeditor.views.main.modules.buildings.races.buildings;

import sfgamedataeditor.views.common.presenters.AbstractModulesPresenter;
import sfgamedataeditor.views.main.modules.buildings.races.buildings.parameters.BuildingsParametersModel;

public class BuildingsPresenter extends AbstractModulesPresenter<BuildingsModelParameter, BuildingsView, BuildingsParametersModel> {

    private final BuildingModelCreator modelCreator = new BuildingModelCreator();

    public BuildingsPresenter(BuildingsView view) {
        super(view);
    }

    @Override
    protected BuildingsParametersModel createModel() {
        int buildingId = getView().getSelectedModuleObjectId();
        return modelCreator.createModel(buildingId);
    }
}
