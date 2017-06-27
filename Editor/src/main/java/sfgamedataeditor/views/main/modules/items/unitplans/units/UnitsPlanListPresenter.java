package sfgamedataeditor.views.main.modules.items.unitplans.units;

import sfgamedataeditor.views.common.presenters.AbstractModulesPresenter;
import sfgamedataeditor.views.main.modules.items.unitplans.units.parameters.UnitsPlansParametersModel;

import javax.swing.*;

public class UnitsPlanListPresenter extends AbstractModulesPresenter<UnitsPlanListModelParameter, UnitsPlanListView, UnitsPlansParametersModel> {

    private final UnitPlansModelCreator modelCreator = new UnitPlansModelCreator();

    public UnitsPlanListPresenter(UnitsPlanListView view) {
        super(view);
    }

    @Override
    protected UnitsPlansParametersModel createModel() {
        int itemId = getView().getSelectedModuleObjectId();
        Icon icon = getView().getSelectedModuleIcon();
        return modelCreator.createModel(itemId, icon);
    }
}
