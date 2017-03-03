package sfgamedataeditor.views.main.modules.buildings.races.buildings;

import sfgamedataeditor.views.common.AbstractModulesPresenter;
import sfgamedataeditor.views.main.modules.buildings.races.buildings.parameters.BuildingsParametersModel;
import sfgamedataeditor.views.utility.ViewTools;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;

public class BuildingsPresenter extends AbstractModulesPresenter<BuildingsModelParameter, BuildingsView, BuildingsParametersModel> {

    private BuildingModelCreator modelCreator = new BuildingModelCreator();

    public BuildingsPresenter(BuildingsView view) {
        super(view);
    }

    @Override
    protected BuildingsParametersModel createModel() {
        String selectedBuildingName = getView().getSelectedModuleName();
        int buildingId = ViewTools.getKeyByPropertyValue(selectedBuildingName, I18NTypes.BUILDING_NAMES_MAPPING);
        Icon icon = getView().getSelectedModuleIcon();
        return modelCreator.createModel(buildingId, icon);
    }
}
