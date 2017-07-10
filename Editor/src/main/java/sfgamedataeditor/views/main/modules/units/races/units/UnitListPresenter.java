package sfgamedataeditor.views.main.modules.units.races.units;

import sfgamedataeditor.views.common.presenters.AbstractModulesPresenter;
import sfgamedataeditor.views.main.modules.units.races.units.parameters.UnitsParametersModel;

import javax.swing.*;

public class UnitListPresenter extends AbstractModulesPresenter<UnitListModelParameter, UnitListView, UnitsParametersModel> {

    private final UnitModelCreator modelCreator = new UnitModelCreator();

    public UnitListPresenter(UnitListView view) {
        super(view);
    }

    @Override
    protected UnitsParametersModel createModel() {
        Integer creatureId = getView().getSelectedModuleObjectId();
        Icon icon = getView().getSelectedModuleIcon();
        return modelCreator.createModel(creatureId);
    }
}
