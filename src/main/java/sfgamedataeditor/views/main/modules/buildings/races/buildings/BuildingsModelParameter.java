package sfgamedataeditor.views.main.modules.buildings.races.buildings;

import sfgamedataeditor.mvc.objects.PresentableView;
import sfgamedataeditor.mvc.objects.SubModuleParameter;
import sfgamedataeditor.views.main.modules.buildings.races.buildings.parameters.BuildingsParametersView;

import java.util.Set;

public class BuildingsModelParameter implements SubModuleParameter {
    private final Set<String> buildingsNames;
    private final String selectedBuildingName;

    public BuildingsModelParameter(Set<String> buildingsNames, String selectedBuildingName) {
        this.buildingsNames = buildingsNames;
        this.selectedBuildingName = selectedBuildingName;
    }

    @Override
    public String getSelectedModuleName() {
        return selectedBuildingName;
    }

    @Override
    public Set<String> getSubPanelsNames() {
        return buildingsNames;
    }

    @Override
    public Class<? extends PresentableView> getSubPanelsViewClass() {
        return BuildingsParametersView.class;
    }
}
