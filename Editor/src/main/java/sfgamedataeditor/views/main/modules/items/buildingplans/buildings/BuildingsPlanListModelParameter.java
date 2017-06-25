package sfgamedataeditor.views.main.modules.items.buildingplans.buildings;

import sfgamedataeditor.mvc.objects.AbstractSubModuleParameter;
import sfgamedataeditor.mvc.objects.PresentableView;
import sfgamedataeditor.views.common.SubViewPanelTuple;
import sfgamedataeditor.views.main.modules.items.buildingplans.buildings.parameters.BuildingsPlansParametersView;

import java.util.List;

public class BuildingsPlanListModelParameter extends AbstractSubModuleParameter {

    private final Integer buildingsRaceType;

    public BuildingsPlanListModelParameter(List<SubViewPanelTuple> panelTuples, String selectedObjectName, Integer buildingsRaceType) {
        super(panelTuples, selectedObjectName);
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
