package sfgamedataeditor.views.main.modules.buildings.races.buildings.parameters;

import sfgamedataeditor.database.buildings.common.BuildingsObject;
import sfgamedataeditor.database.buildings.common.BuildingsTableService;
import sfgamedataeditor.database.text.TextTableService;
import sfgamedataeditor.mvc.ModelCreator;
import sfgamedataeditor.views.common.SubViewPanelTuple;
import sfgamedataeditor.views.main.modules.buildings.races.buildings.BuildingsModel;
import sfgamedataeditor.views.main.modules.buildings.races.buildings.BuildingsModelParameter;

import java.util.List;

public class BuildingsFromBuildingsParametersModelCreator implements ModelCreator<BuildingsModel, BuildingsParametersModel> {

    @Override
    public BuildingsModel createModel(BuildingsParametersModel childModel) {
        BuildingsObject buildingsObject = childModel.getParameter().getBuildingsObject();
        Integer raceId = buildingsObject.raceId;
        List<SubViewPanelTuple> buildingsNames = BuildingsTableService.INSTANCE.getBuildingsNamesByRaceId(raceId);

        Integer nameId = buildingsObject.nameId;
        String selectedBuildingName = TextTableService.INSTANCE.getObjectName(nameId);
        BuildingsModelParameter parameter = new BuildingsModelParameter(buildingsNames, selectedBuildingName);
        return new BuildingsModel(parameter);
    }
}
