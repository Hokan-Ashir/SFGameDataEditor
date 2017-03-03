package sfgamedataeditor.views.main.modules.items.unitplans.units;


import sfgamedataeditor.mvc.ModelCreator;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.common.ModulesModel;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.util.HashMap;
import java.util.Map;

public class UnitsPlansFromUnitsPlansListModelCreator implements ModelCreator<ModulesModel, UnitsPlanListModel> {

    private Map<String, String> unitRaceTypeToNameMapping = new HashMap<>();

    public UnitsPlansFromUnitsPlansListModelCreator() {
        addMapping(unitRaceTypeToNameMapping, "races.humans", "items.unit.plan.in.inventory.humans");
        addMapping(unitRaceTypeToNameMapping, "races.elves", "items.unit.plan.in.inventory.elves");
        addMapping(unitRaceTypeToNameMapping, "races.dwarves", "items.unit.plan.in.inventory.dwarves");
        addMapping(unitRaceTypeToNameMapping, "races.orcs", "items.unit.plan.in.inventory.orcs");
        addMapping(unitRaceTypeToNameMapping, "races.trolls", "items.unit.plan.in.inventory.trolls");
        addMapping(unitRaceTypeToNameMapping, "races.dark.elves", "items.unit.plan.in.inventory.dark.elves");
    }

    private void addMapping(Map<String, String> map, String raceI18nKey, String itemTypeI18nKey) {
        map.put(I18NService.INSTANCE.getMessage(I18NTypes.ITEM_TYPES_NAME_MAPPING, itemTypeI18nKey), I18NService.INSTANCE.getMessage(I18NTypes.COMMON, raceI18nKey));
    }

    @Override
    public ModulesModel createModel(UnitsPlanListModel childModel) {
        Integer unitRaceType = childModel.getParameter().getUnitsRaceType();
        String raceName = unitRaceTypeToNameMapping.get(String.valueOf(unitRaceType));
        ModuleParameter parameter = new ModuleParameter(raceName);
        return new ModulesModel(parameter);
    }
}
