package sfgamedataeditor.views.main.modules.items.buildingplans.buildings;

import sfgamedataeditor.mvc.objects.AbstractSubModuleParameter;
import sfgamedataeditor.mvc.objects.PresentableView;
import sfgamedataeditor.views.main.modules.items.buildingplans.buildings.parameters.BuildingsPlansParametersView;

import java.util.Set;

public class BuildingsPlanListModelParameter extends AbstractSubModuleParameter {

    private final Integer buildingsRaceType;

    public BuildingsPlanListModelParameter(Set<String> objectNames, String selectedObjectName, Integer buildingsRaceType) {
        super(objectNames, selectedObjectName);
        this.buildingsRaceType = buildingsRaceType;
    }

    public Integer getBuildingsRaceType() {
        return buildingsRaceType;
    }

    @Override
    public Class<? extends PresentableView> getSubPanelsViewClass() {
        return BuildingsPlansParametersView.class;
    }
}
