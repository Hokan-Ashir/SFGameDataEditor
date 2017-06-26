package sfgamedataeditor.views.main.modules.items.unitplans;

import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.common.SubViewPanelTuple;
import sfgamedataeditor.views.common.presenters.AbstractModulesPresenter;
import sfgamedataeditor.views.main.modules.items.unitplans.units.UnitsPlanListModel;
import sfgamedataeditor.views.main.modules.items.unitplans.units.UnitsPlanListModelParameter;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UnitPlansRacesPresenter extends AbstractModulesPresenter<ModuleParameter, UnitPlansRacesView, UnitsPlanListModel> {

    public UnitPlansRacesPresenter(UnitPlansRacesView view) {
        super(view);
    }

    @Override
    protected UnitsPlanListModel createModel() {
        Integer itemType = getView().getSelectedModuleObjectId();
        List<SubViewPanelTuple> buildingsNames = ItemPriceParametersTableService.INSTANCE.getItemsByItemType(itemType);
        UnitsPlanListModelParameter parameter = new UnitsPlanListModelParameter(buildingsNames, null, itemType);
        return new UnitsPlanListModel(parameter);
    }
}
