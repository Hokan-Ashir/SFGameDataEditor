package sfgamedataeditor.views.main.modules.buildings.races.buildings.parameters;

import sfgamedataeditor.database.buildings.common.BuildingsObject;
import sfgamedataeditor.database.buildings.requirements.BuildingsRequirementsObject;

import java.util.List;

public class BuildingsParametersModelParameter {
    private final BuildingsObject buildingsObject;
    private final List<BuildingsRequirementsObject> requirementsObjects;
    // TODO add icon


    public BuildingsParametersModelParameter(BuildingsObject buildingsObject, List<BuildingsRequirementsObject> requirementsObjects) {
        this.buildingsObject = buildingsObject;
        this.requirementsObjects = requirementsObjects;
    }

    public BuildingsObject getBuildingsObject() {
        return buildingsObject;
    }

    public List<BuildingsRequirementsObject> getRequirementsObjects() {
        return requirementsObjects;
    }
}
