package sfgamedataeditor.events.processing.strategies.content.modelcreators.buildings;

import sfgamedataeditor.database.buildings.common.BuildingsTableService;
import sfgamedataeditor.mvc.ModelCreator;
import sfgamedataeditor.views.main.modules.buildings.races.buildings.BuildingsModel;
import sfgamedataeditor.views.main.modules.buildings.races.buildings.BuildingsModelParameter;
import sfgamedataeditor.views.main.modules.buildings.races.buildings.parameters.BuildingsParametersModel;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.util.Set;

public class BuildingsFromBuildingsParametersModelCreator implements ModelCreator<BuildingsModel, BuildingsParametersModel> {

    @Override
    public BuildingsModel createModel(BuildingsParametersModel childModel) {
        Integer raceId = childModel.getParameter().getBuildingsObject().raceId;
        Set<String> buildingsNames = BuildingsTableService.INSTANCE.getBuildingsNamesByRaceId(raceId);

        Integer buildingId = childModel.getParameter().getBuildingsObject().buildingId;
        String selectedBuildingName = I18NService.INSTANCE.getMessage(I18NTypes.BUILDING_NAMES_MAPPING, String.valueOf(buildingId));
        BuildingsModelParameter parameter = new BuildingsModelParameter(buildingsNames, selectedBuildingName);
        return new BuildingsModel(parameter);
    }
}
