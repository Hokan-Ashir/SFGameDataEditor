package sfgamedataeditor.views.main.modules.items.buildingplans;

import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.views.common.AbstractModulesPresenter;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.main.modules.items.buildingplans.buildings.BuildingsPlanListModel;
import sfgamedataeditor.views.main.modules.items.buildingplans.buildings.BuildingsPlanListModelParameter;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BuildingPlansRacesPresenter extends AbstractModulesPresenter<ModuleParameter, BuildingPlansRacesView, BuildingsPlanListModel> {

    private Map<String, String> buildingRaceTypeToNameMapping = new HashMap<>();

    public BuildingPlansRacesPresenter(BuildingPlansRacesView view) {
        super(view);
        initializeBuildingRaceTypeToNameMapping();
    }

    private void initializeBuildingRaceTypeToNameMapping() {
        addMapping(buildingRaceTypeToNameMapping, "races.humans", "items.building.plan.in.inventory.humans");
        addMapping(buildingRaceTypeToNameMapping, "races.elves", "items.building.plan.in.inventory.elves");
        addMapping(buildingRaceTypeToNameMapping, "races.dwarves", "items.building.plan.in.inventory.dwarves");
        addMapping(buildingRaceTypeToNameMapping, "races.orcs", "items.building.plan.in.inventory.orcs");
        addMapping(buildingRaceTypeToNameMapping, "races.trolls", "items.building.plan.in.inventory.trolls");
        addMapping(buildingRaceTypeToNameMapping, "races.dark.elves", "items.building.plan.in.inventory.dark.elves");
    }

    private void addMapping(Map<String, String> map, String raceI18nKey, String itemTypeI18nKey) {
        map.put(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, raceI18nKey), I18NService.INSTANCE.getMessage(I18NTypes.ITEM_TYPES_NAME_MAPPING, itemTypeI18nKey));
    }

    @Override
    protected BuildingsPlanListModel createModel() {
        String selectedModuleName = getView().getSelectedModuleName();
        Integer itemType = Integer.valueOf(buildingRaceTypeToNameMapping.get(selectedModuleName));
        Set<String> buildingsNames = ItemPriceParametersTableService.INSTANCE.getItemsByItemType(itemType);
        BuildingsPlanListModelParameter parameter = new BuildingsPlanListModelParameter(buildingsNames, null, itemType);
        return new BuildingsPlanListModel(parameter);
    }
}
