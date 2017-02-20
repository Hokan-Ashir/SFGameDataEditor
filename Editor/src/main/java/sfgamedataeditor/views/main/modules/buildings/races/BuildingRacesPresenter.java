package sfgamedataeditor.views.main.modules.buildings.races;

import sfgamedataeditor.database.buildings.common.BuildingsTableService;
import sfgamedataeditor.views.common.AbstractModulesPresenter;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.main.modules.buildings.races.buildings.BuildingsModel;
import sfgamedataeditor.views.main.modules.buildings.races.buildings.BuildingsModelParameter;

import java.util.Set;

public class BuildingRacesPresenter extends AbstractModulesPresenter<ModuleParameter, BuildingRacesView, BuildingsModel> {

    public BuildingRacesPresenter(BuildingRacesView view) {
        super(view);
    }

    @Override
    protected BuildingsModel createModel() {
        String selectedRace = getView().getSelectedModuleValue();
        Set<String> buildingNames = BuildingsTableService.INSTANCE.getBuildingsNamesByRaceName(selectedRace);
        BuildingsModelParameter parameter = new BuildingsModelParameter(buildingNames, null);
        return new BuildingsModel(parameter);
    }
}
