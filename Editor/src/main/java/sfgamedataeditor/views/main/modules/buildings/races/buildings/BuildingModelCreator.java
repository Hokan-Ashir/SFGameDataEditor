package sfgamedataeditor.views.main.modules.buildings.races.buildings;

import sfgamedataeditor.common.cache.icons.ImageIconsCache;
import sfgamedataeditor.database.buildings.army.requirements.BuildingsArmyRequirementsObject;
import sfgamedataeditor.database.buildings.army.requirements.BuildingsArmyRequirementsTableService;
import sfgamedataeditor.database.buildings.common.BuildingsObject;
import sfgamedataeditor.database.buildings.common.BuildingsTableService;
import sfgamedataeditor.database.buildings.requirements.BuildingsRequirementsObject;
import sfgamedataeditor.database.buildings.requirements.BuildingsRequirementsTableService;
import sfgamedataeditor.views.common.model.creators.ModelCreator;
import sfgamedataeditor.views.main.modules.buildings.races.buildings.parameters.BuildingsParametersModel;
import sfgamedataeditor.views.main.modules.buildings.races.buildings.parameters.BuildingsParametersModelParameter;

import javax.swing.*;
import java.util.List;

public class BuildingModelCreator implements ModelCreator<BuildingsParametersModel> {
    @Override
    public BuildingsParametersModel createModel(int objectId) {
        BuildingsObject buildingsObject = BuildingsTableService.INSTANCE.getBuildingObjectByBuildingId(objectId);
        List<BuildingsRequirementsObject> requirementsObjects = BuildingsRequirementsTableService.INSTANCE.getBuildingRequirementsObjectsByBuildingId(objectId);
        List<BuildingsArmyRequirementsObject> buildingArmyObjects = BuildingsArmyRequirementsTableService.INSTANCE.getBuildingArmyObjectByBuildingId(objectId);
        Icon icon = ImageIconsCache.INSTANCE.getImageIcon(getIconPath(), objectId);
        BuildingsParametersModelParameter parameter = new BuildingsParametersModelParameter(buildingsObject, requirementsObjects, buildingArmyObjects, icon);
        return new BuildingsParametersModel(parameter);
    }

    @Override
    public String getIconPath() {
        return "/images/buildings/";
    }
}
