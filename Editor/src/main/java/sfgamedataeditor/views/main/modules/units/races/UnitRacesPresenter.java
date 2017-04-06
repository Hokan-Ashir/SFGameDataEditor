package sfgamedataeditor.views.main.modules.units.races;

import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.common.presenters.AbstractModulesPresenter;
import sfgamedataeditor.views.main.modules.units.races.units.UnitListModel;
import sfgamedataeditor.views.main.modules.units.races.units.UnitListModelParameter;

import java.util.Set;

public class UnitRacesPresenter extends AbstractModulesPresenter<ModuleParameter, UnitRacesView, UnitListModel> {

    public UnitRacesPresenter(UnitRacesView view) {
        super(view);
    }

    @Override
    protected UnitListModel createModel() {
        String selectedRaceName = getView().getSelectedModuleName();
        Set<String> unitNames = UnitMapping.INSTANCE.getUnitNames(selectedRaceName);
        UnitListModelParameter parameter = new UnitListModelParameter(unitNames, null);
        return new UnitListModel(parameter);
    }
}
