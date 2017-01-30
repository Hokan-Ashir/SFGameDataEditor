package sfgamedataeditor.views.main.modules.units.races.units;

import sfgamedataeditor.mvc.objects.SubModuleParameter;

import java.util.List;

public class UnitListModelParameter implements SubModuleParameter {
    private final List<String> unitNames;
    private final String selectedUnitName;

    public UnitListModelParameter(List<String> unitNames, String selectedUnitName) {
        this.unitNames = unitNames;
        this.selectedUnitName = selectedUnitName;
    }

    public List<String> getUnitNames() {
        return unitNames;
    }

    @Override
    public String getSelectedModuleName() {
        return selectedUnitName;
    }
}
