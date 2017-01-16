package sfgamedataeditor.views.main.modules.units.races;

import sfgamedataeditor.views.common.AbstractModulesPresenter;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.main.modules.units.races.units.UnitListModel;
import sfgamedataeditor.views.main.modules.units.races.units.UnitListModelParameter;

import java.util.List;

public class UnitRacesPresenter extends AbstractModulesPresenter<ModuleParameter, UnitRacesView, UnitListModel> {

    public UnitRacesPresenter(UnitRacesView view) {
        super(view);
    }

    @Override
    protected UnitListModel createModel() {
        String selectedRaceName = getView().getSelectedModuleValue();
        List<String> unitNames = UnitMapping.INSTANCE.getUnitNames(selectedRaceName);
        UnitListModelParameter parameter = new UnitListModelParameter(unitNames, null);
        return new UnitListModel(parameter);
    }

    @Override
    public void updateView() {
        if (getModel() == null) {
            setModulesComboBoxValue(null);
            return;
        }

        String moduleName = getModel().getParameter().getModuleName();
        if (isElementExistsInComboBox(moduleName)) {
            setModulesComboBoxValue(moduleName);
        } else {
            setModulesComboBoxValue(null);
        }
    }
}
