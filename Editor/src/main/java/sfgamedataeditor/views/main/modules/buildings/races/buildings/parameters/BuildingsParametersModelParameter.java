package sfgamedataeditor.views.main.modules.buildings.races.buildings.parameters;

import sfgamedataeditor.database.buildings.army.requirements.BuildingsArmyRequirementsObject;
import sfgamedataeditor.database.buildings.common.BuildingsObject;
import sfgamedataeditor.database.buildings.requirements.BuildingsRequirementsObject;

import javax.swing.*;
import java.util.List;

public class BuildingsParametersModelParameter {
    private final BuildingsObject buildingsObject;
    private final List<BuildingsRequirementsObject> requirementsObjects;
    private final List<BuildingsArmyRequirementsObject> buildingsArmyRequirementsObjects;
    private final Icon icon;

    public BuildingsParametersModelParameter(BuildingsObject buildingsObject,
                                             List<BuildingsRequirementsObject> requirementsObjects,
                                             List<BuildingsArmyRequirementsObject> buildingsArmyRequirementsObjects,
                                             Icon icon) {
        this.buildingsObject = buildingsObject;
        this.requirementsObjects = requirementsObjects;
        this.buildingsArmyRequirementsObjects = buildingsArmyRequirementsObjects;
        this.icon = icon;
    }

    public Icon getIcon() {
        return icon;
    }

    public BuildingsObject getBuildingsObject() {
        return buildingsObject;
    }

    public List<BuildingsArmyRequirementsObject> getBuildingsArmyRequirementsObjects() {
        return buildingsArmyRequirementsObjects;
    }

    public List<BuildingsRequirementsObject> getRequirementsObjects() {
        return requirementsObjects;
    }
}
