package sfgamedataeditor.views.main.modules.items.unitplans.units;

import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.views.common.AbstractModulesPresenter;
import sfgamedataeditor.views.main.modules.items.unitplans.units.parameters.UnitsPlansParametersModel;

import javax.swing.*;

public class UnitsPlanListPresenter extends AbstractModulesPresenter<UnitsPlanListModelParameter, UnitsPlanListView, UnitsPlansParametersModel> {

    private final UnitPlansModelCreator modelCreator = new UnitPlansModelCreator();

    public UnitsPlanListPresenter(UnitsPlanListView view) {
        super(view);
    }

    @Override
    protected UnitsPlansParametersModel createModel() {
        String selectedUnitName = getView().getSelectedModuleName();
        Integer unitRaceType = getModel().getParameter().getUnitsRaceType();
        int itemId = ItemPriceParametersTableService.INSTANCE.getItemIdByItemNameAndType(selectedUnitName, unitRaceType);
        Icon icon = getView().getSelectedModuleIcon();
        return modelCreator.createModel(itemId, icon);
    }
}
