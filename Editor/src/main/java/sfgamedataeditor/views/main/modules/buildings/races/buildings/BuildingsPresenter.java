package sfgamedataeditor.views.main.modules.buildings.races.buildings;

import sfgamedataeditor.database.buildings.army.requirements.BuildingsArmyRequirementsObject;
import sfgamedataeditor.database.buildings.army.requirements.BuildingsArmyRequirementsTableService;
import sfgamedataeditor.database.buildings.common.BuildingsObject;
import sfgamedataeditor.database.buildings.common.BuildingsTableService;
import sfgamedataeditor.database.buildings.requirements.BuildingsRequirementsObject;
import sfgamedataeditor.database.buildings.requirements.BuildingsRequirementsTableService;
import sfgamedataeditor.views.common.AbstractModulesPresenter;
import sfgamedataeditor.views.main.modules.buildings.races.buildings.parameters.BuildingsParametersModel;
import sfgamedataeditor.views.main.modules.buildings.races.buildings.parameters.BuildingsParametersModelParameter;

import javax.swing.*;
import java.util.List;

public class BuildingsPresenter extends AbstractModulesPresenter<BuildingsModelParameter, BuildingsView, BuildingsParametersModel> {

    public BuildingsPresenter(BuildingsView view) {
        super(view);
    }

    @Override
    protected BuildingsParametersModel createModel() {
        String selectedBuildingName = getView().getSelectedModuleValue();
        BuildingsObject buildingsObject = BuildingsTableService.INSTANCE.getBuildingObjectByBuildingName(selectedBuildingName);
        List<BuildingsRequirementsObject> requirementsObjects = BuildingsRequirementsTableService.INSTANCE.getBuildingRequirementsObjectsByBuildingName(selectedBuildingName);
        List<BuildingsArmyRequirementsObject> buildingArmyObjects = BuildingsArmyRequirementsTableService.INSTANCE.getBuildingArmyObjectByBuildingName(selectedBuildingName);
        Icon icon = getView().getSelectedModuleIcon();
        BuildingsParametersModelParameter parameter = new BuildingsParametersModelParameter(buildingsObject, requirementsObjects, buildingArmyObjects, icon);
        return new BuildingsParametersModel(parameter);
    }
}
