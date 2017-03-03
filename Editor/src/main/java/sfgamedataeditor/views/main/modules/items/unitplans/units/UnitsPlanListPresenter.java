package sfgamedataeditor.views.main.modules.items.unitplans.units;

import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersObject;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.views.common.AbstractModulesPresenter;
import sfgamedataeditor.views.main.modules.items.unitplans.units.parameters.UnitsPlansParametersModel;
import sfgamedataeditor.views.main.modules.items.unitplans.units.parameters.UnitsPlansParametersModelParameter;

import javax.swing.*;

public class UnitsPlanListPresenter extends AbstractModulesPresenter<UnitsPlanListModelParameter, UnitsPlanListView, UnitsPlansParametersModel> {

    public UnitsPlanListPresenter(UnitsPlanListView view) {
        super(view);
    }

    @Override
    protected UnitsPlansParametersModel createModel() {
        String selectedUnitName = getView().getSelectedModuleName();
        Integer unitRaceType = getModel().getParameter().getUnitsRaceType();
        int itemId = ItemPriceParametersTableService.INSTANCE.getItemIdByItemNameAndType(selectedUnitName, unitRaceType);
        Icon icon = getView().getSelectedModuleIcon();
        ItemPriceParametersObject itemPriceObject = ItemPriceParametersTableService.INSTANCE.getObjectByItemId(itemId);
        UnitsPlansParametersModelParameter parameter = new UnitsPlansParametersModelParameter(itemPriceObject, icon);
        return new UnitsPlansParametersModel(parameter);
    }
}
