package sfgamedataeditor.views.main.modules.items.buildingplans;

import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.common.presenters.AbstractModulesPresenter;
import sfgamedataeditor.views.main.modules.items.buildingplans.buildings.BuildingsPlanListModel;
import sfgamedataeditor.views.main.modules.items.buildingplans.buildings.BuildingsPlanListModelParameter;

import java.util.Set;

public class BuildingPlansRacesPresenter extends AbstractModulesPresenter<ModuleParameter, BuildingPlansRacesView, BuildingsPlanListModel> {

    public BuildingPlansRacesPresenter(BuildingPlansRacesView view) {
        super(view);
    }

    @Override
    protected BuildingsPlanListModel createModel() {
        String selectedModuleName = getView().getSelectedModuleName();
        Integer itemType = Integer.valueOf(BuildingPlansMapping.INSTANCE.getRaceMappingValue(selectedModuleName));
        Set<String> buildingsNames = ItemPriceParametersTableService.INSTANCE.getItemsByItemType(itemType);
        BuildingsPlanListModelParameter parameter = new BuildingsPlanListModelParameter(buildingsNames, null, itemType);
        return new BuildingsPlanListModel(parameter);
    }
}
