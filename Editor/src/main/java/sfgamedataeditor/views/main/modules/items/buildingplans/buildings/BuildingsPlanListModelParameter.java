package sfgamedataeditor.views.main.modules.items.buildingplans.buildings;

import sfgamedataeditor.mvc.objects.AbstractSubModuleParameter;
import sfgamedataeditor.mvc.objects.PresentableView;
import sfgamedataeditor.views.common.SubViewPanelTuple;
import sfgamedataeditor.views.main.modules.items.buildingplans.buildings.parameters.BuildingsPlansParametersView;

import java.util.List;

public class BuildingsPlanListModelParameter extends AbstractSubModuleParameter {

    public BuildingsPlanListModelParameter(List<SubViewPanelTuple> panelTuples, String selectedObjectName) {
        super(panelTuples, selectedObjectName);
    }

    @Override
    public Class<? extends PresentableView> getSubPanelsViewClass() {
        return BuildingsPlansParametersView.class;
    }
}
