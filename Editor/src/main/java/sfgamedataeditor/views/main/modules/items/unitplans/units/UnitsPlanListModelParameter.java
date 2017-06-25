package sfgamedataeditor.views.main.modules.items.unitplans.units;

import sfgamedataeditor.mvc.objects.AbstractSubModuleParameter;
import sfgamedataeditor.mvc.objects.PresentableView;
import sfgamedataeditor.views.common.SubViewPanelTuple;
import sfgamedataeditor.views.main.modules.items.unitplans.units.parameters.UnitsPlansParametersView;

import java.util.List;

public class UnitsPlanListModelParameter extends AbstractSubModuleParameter {

    private final Integer unitsRaceType;

    public UnitsPlanListModelParameter(List<SubViewPanelTuple> panelTuples, String selectedObjectName, Integer unitsRaceType) {
        super(panelTuples, selectedObjectName);
        this.unitsRaceType = unitsRaceType;
    }

    public Integer getUnitsRaceType() {
        return unitsRaceType;
    }

    @Override
    public Class<? extends PresentableView> getSubPanelsViewClass() {
        return UnitsPlansParametersView.class;
    }
}
