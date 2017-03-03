package sfgamedataeditor.views.main.modules.units.races.units;

import sfgamedataeditor.database.creatures.common.CreatureCommonParametersTableService;
import sfgamedataeditor.views.common.AbstractModulesPresenter;
import sfgamedataeditor.views.main.modules.units.races.units.parameters.UnitsParametersModel;

import javax.swing.*;

public class UnitListPresenter extends AbstractModulesPresenter<UnitListModelParameter, UnitListView, UnitsParametersModel> {

    private final UnitModelCreator modelCreator = new UnitModelCreator();

    public UnitListPresenter(UnitListView view) {
        super(view);
    }

    @Override
    protected UnitsParametersModel createModel() {
        String selectedCreatureName = getView().getSelectedModuleName();
        Integer creatureId = CreatureCommonParametersTableService.INSTANCE.getCreatureIdByName(selectedCreatureName);
        Icon icon = getView().getSelectedModuleIcon();
        return modelCreator.createModel(creatureId, icon);
    }
}
