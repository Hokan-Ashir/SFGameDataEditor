package sfgamedataeditor.views.main.modules.buildings.races;

import sfgamedataeditor.database.buildings.common.BuildingsTableService;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.common.ObjectTuple;
import sfgamedataeditor.views.common.presenters.AbstractModulesPresenter;
import sfgamedataeditor.views.main.modules.buildings.races.buildings.BuildingsModel;
import sfgamedataeditor.views.main.modules.buildings.races.buildings.BuildingsModelParameter;

import java.util.List;

public class BuildingRacesPresenter extends AbstractModulesPresenter<ModuleParameter, BuildingRacesView, BuildingsModel> {

    public BuildingRacesPresenter(BuildingRacesView view) {
        super(view);
    }

    @Override
    protected BuildingsModel createModel() {
        Integer raceId = getView().getSelectedModuleObjectId();
        List<ObjectTuple> buildingNames = BuildingsTableService.INSTANCE.getBuildingsNamesByRaceId(raceId);
        BuildingsModelParameter parameter = new BuildingsModelParameter(buildingNames, null);
        return new BuildingsModel(parameter);
    }
}
