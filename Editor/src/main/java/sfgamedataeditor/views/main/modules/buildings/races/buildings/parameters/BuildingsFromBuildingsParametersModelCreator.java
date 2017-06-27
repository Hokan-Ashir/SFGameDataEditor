package sfgamedataeditor.views.main.modules.buildings.races.buildings.parameters;

import sfgamedataeditor.database.buildings.common.BuildingsObject;
import sfgamedataeditor.database.buildings.common.BuildingsTableService;
import sfgamedataeditor.database.text.TextTableService;
import sfgamedataeditor.mvc.ModelCreator;
import sfgamedataeditor.views.common.ObjectTuple;
import sfgamedataeditor.views.main.modules.buildings.races.buildings.BuildingsModel;
import sfgamedataeditor.views.main.modules.buildings.races.buildings.BuildingsModelParameter;

import java.util.List;

public class BuildingsFromBuildingsParametersModelCreator implements ModelCreator<BuildingsModel, BuildingsParametersModel> {

    @Override
    public BuildingsModel createModel(BuildingsParametersModel childModel) {
        BuildingsObject buildingsObject = childModel.getParameter().getBuildingsObject();
        List<ObjectTuple> buildingsNames = BuildingsTableService.INSTANCE.getBuildingsNamesByRaceId(buildingsObject.raceId);
        ObjectTuple selectedBuildingName = TextTableService.INSTANCE.getObjectTuple(buildingsObject.nameId, buildingsObject.buildingId);
        BuildingsModelParameter parameter = new BuildingsModelParameter(buildingsNames, selectedBuildingName);
        return new BuildingsModel(parameter);
    }
}
