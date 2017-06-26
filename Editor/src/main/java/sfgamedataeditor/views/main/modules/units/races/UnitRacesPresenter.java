package sfgamedataeditor.views.main.modules.units.races;

import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.common.SubViewPanelTuple;
import sfgamedataeditor.views.common.presenters.AbstractModulesPresenter;
import sfgamedataeditor.views.main.modules.units.races.units.UnitListModel;
import sfgamedataeditor.views.main.modules.units.races.units.UnitListModelParameter;

import java.util.List;

public class UnitRacesPresenter extends AbstractModulesPresenter<ModuleParameter, UnitRacesView, UnitListModel> {

    public UnitRacesPresenter(UnitRacesView view) {
        super(view);
    }

    @Override
    protected UnitListModel createModel() {
        String selectedRaceName = getView().getSelectedModuleName();
        Integer raceId = getView().getSelectedModuleObjectId();
        List<SubViewPanelTuple> unitNames = UnitMapping.INSTANCE.getUnitNames(new SubViewPanelTuple(selectedRaceName, raceId));
        UnitListModelParameter parameter = new UnitListModelParameter(unitNames, null);
        return new UnitListModel(parameter);
    }
}
