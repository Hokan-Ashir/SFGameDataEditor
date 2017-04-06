package sfgamedataeditor.views.main.modules.items.unitplans;

import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.common.presenters.AbstractModulesPresenter;
import sfgamedataeditor.views.main.modules.items.unitplans.units.UnitsPlanListModel;
import sfgamedataeditor.views.main.modules.items.unitplans.units.UnitsPlanListModelParameter;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class UnitPlansRacesPresenter extends AbstractModulesPresenter<ModuleParameter, UnitPlansRacesView, UnitsPlanListModel> {

    private final Map<String, String> unitRaceTypeToNameMapping = new HashMap<>();

    public UnitPlansRacesPresenter(UnitPlansRacesView view) {
        super(view);
        initializeUnitRaceTypeToNameMapping();
    }

    private void initializeUnitRaceTypeToNameMapping() {
        addMapping(unitRaceTypeToNameMapping, "races.humans", "items.unit.plan.in.inventory.humans");
        addMapping(unitRaceTypeToNameMapping, "races.elves", "items.unit.plan.in.inventory.elves");
        addMapping(unitRaceTypeToNameMapping, "races.dwarves", "items.unit.plan.in.inventory.dwarves");
        addMapping(unitRaceTypeToNameMapping, "races.orcs", "items.unit.plan.in.inventory.orcs");
        addMapping(unitRaceTypeToNameMapping, "races.trolls", "items.unit.plan.in.inventory.trolls");
        addMapping(unitRaceTypeToNameMapping, "races.dark.elves", "items.unit.plan.in.inventory.dark.elves");
    }

    private void addMapping(Map<String, String> map, String raceI18nKey, String itemTypeI18nKey) {
        map.put(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, raceI18nKey), I18NService.INSTANCE.getMessage(I18NTypes.ITEM_TYPES_NAME_MAPPING, itemTypeI18nKey));
    }

    @Override
    protected UnitsPlanListModel createModel() {
        String selectedModuleName = getView().getSelectedModuleName();
        Integer itemType = Integer.valueOf(unitRaceTypeToNameMapping.get(selectedModuleName));
        Set<String> buildingsNames = ItemPriceParametersTableService.INSTANCE.getItemsByItemType(itemType);
        UnitsPlanListModelParameter parameter = new UnitsPlanListModelParameter(buildingsNames, null, itemType);
        return new UnitsPlanListModel(parameter);
    }
}
