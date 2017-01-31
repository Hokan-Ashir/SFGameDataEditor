package sfgamedataeditor.views.main.modules.units.races.units;

import sfgamedataeditor.mvc.objects.PresentableView;
import sfgamedataeditor.mvc.objects.SubModuleParameter;
import sfgamedataeditor.views.main.modules.units.races.units.parameters.UnitsParametersView;

import java.util.Set;

public class UnitListModelParameter implements SubModuleParameter {
    private final Set<String> unitNames;
    private final String selectedUnitName;

    public UnitListModelParameter(Set<String> unitNames, String selectedUnitName) {
        this.unitNames = unitNames;
        this.selectedUnitName = selectedUnitName;
    }

    @Override
    public String getSelectedModuleName() {
        return selectedUnitName;
    }

    @Override
    public Set<String> getSubPanelsNames() {
        return unitNames;
    }

    @Override
    public Class<? extends PresentableView> getSubPanelsViewClass() {
        return UnitsParametersView.class;
    }
}
