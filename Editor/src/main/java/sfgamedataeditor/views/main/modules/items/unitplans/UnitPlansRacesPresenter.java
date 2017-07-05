package sfgamedataeditor.views.main.modules.items.unitplans;

import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.common.ObjectTuple;
import sfgamedataeditor.views.common.presenters.AbstractModulesPresenter;
import sfgamedataeditor.views.main.modules.items.unitplans.units.UnitsPlanListModel;
import sfgamedataeditor.views.main.modules.items.unitplans.units.UnitsPlanListModelParameter;

import java.util.List;

public class UnitPlansRacesPresenter extends AbstractModulesPresenter<ModuleParameter, UnitPlansRacesView, UnitsPlanListModel> {

    public UnitPlansRacesPresenter(UnitPlansRacesView view) {
        super(view);
    }

    @Override
    protected UnitsPlanListModel createModel() {
        String raceName = getView().getSelectedModuleName();
        Integer itemType = UnitRaceToItemPlansMapping.INSTANCE.getRaceItemType(raceName);
        List<ObjectTuple> unitTuples = ItemPriceParametersTableService.INSTANCE.getItemsByItemType(itemType);
        UnitsPlanListModelParameter parameter = new UnitsPlanListModelParameter(unitTuples, null);
        return new UnitsPlanListModel(parameter);
    }
}
