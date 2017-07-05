package sfgamedataeditor.views.main.modules.items.buildingplans;

import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.common.ObjectTuple;
import sfgamedataeditor.views.common.presenters.AbstractModulesPresenter;
import sfgamedataeditor.views.main.modules.items.buildingplans.buildings.BuildingsPlanListModel;
import sfgamedataeditor.views.main.modules.items.buildingplans.buildings.BuildingsPlanListModelParameter;

import java.util.List;

public class BuildingPlansRacesPresenter extends AbstractModulesPresenter<ModuleParameter, BuildingPlansRacesView, BuildingsPlanListModel> {

    public BuildingPlansRacesPresenter(BuildingPlansRacesView view) {
        super(view);
    }

    @Override
    protected BuildingsPlanListModel createModel() {
        String raceName = getView().getSelectedModuleName();
        Integer itemType = BuildingRaceToItemPlansMapping.INSTANCE.getRaceItemType(raceName);
        List<ObjectTuple> buildingsNames = ItemPriceParametersTableService.INSTANCE.getItemsByItemType(itemType);
        BuildingsPlanListModelParameter parameter = new BuildingsPlanListModelParameter(buildingsNames, null);
        return new BuildingsPlanListModel(parameter);
    }
}
