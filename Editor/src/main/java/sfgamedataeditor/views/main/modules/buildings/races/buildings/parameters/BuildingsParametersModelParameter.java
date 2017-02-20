package sfgamedataeditor.views.main.modules.buildings.races.buildings.parameters;

import sfgamedataeditor.database.buildings.common.BuildingsObject;
import sfgamedataeditor.database.buildings.requirements.BuildingsRequirementsObject;

import javax.swing.*;
import java.util.List;

public class BuildingsParametersModelParameter {
    private final BuildingsObject buildingsObject;
    private final List<BuildingsRequirementsObject> requirementsObjects;
    private final Icon icon;

    public BuildingsParametersModelParameter(BuildingsObject buildingsObject, List<BuildingsRequirementsObject> requirementsObjects, Icon icon) {
        this.buildingsObject = buildingsObject;
        this.requirementsObjects = requirementsObjects;
        this.icon = icon;
    }

    public Icon getIcon() {
        return icon;
    }

    public BuildingsObject getBuildingsObject() {
        return buildingsObject;
    }

    public List<BuildingsRequirementsObject> getRequirementsObjects() {
        return requirementsObjects;
    }
}
