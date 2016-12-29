package sfgamedataeditor.views.main.modules.units.races.units;

import java.util.List;

public class UnitListModelParameter {
    private final List<String> unitNames;
    private final String selectedUnitName;

    public UnitListModelParameter(List<String> unitNames, String selectedUnitName) {
        this.unitNames = unitNames;
        this.selectedUnitName = selectedUnitName;
    }

    public List<String> getUnitNames() {
        return unitNames;
    }

    public String getSelectedUnitName() {
        return selectedUnitName;
    }
}
