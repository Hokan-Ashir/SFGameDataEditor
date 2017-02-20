package sfgamedataeditor.events.processing.strategies.content.modelcreators.buildings;

import sfgamedataeditor.database.buildings.common.BuildingsTableService;
import sfgamedataeditor.mvc.ModelCreator;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.common.ModulesModel;
import sfgamedataeditor.views.main.modules.buildings.races.buildings.BuildingsModel;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

public class BuildingRacesFromBuildingsModelCreator implements ModelCreator<ModulesModel, BuildingsModel> {

    @Override
    public ModulesModel createModel(BuildingsModel childModel) {
        // it's not important which object we choose, cause "getRaceIdByBuildingName" will correct identify race
        String buildingName = childModel.getParameter().getSubPanelsNames().iterator().next();
        int raceId = BuildingsTableService.INSTANCE.getRaceIdByBuildingName(buildingName);
        String raceName = I18NService.INSTANCE.getMessage(I18NTypes.RACES, String.valueOf(raceId));
        ModuleParameter parameter = new ModuleParameter(raceName);
        return new ModulesModel(parameter);
    }
}
