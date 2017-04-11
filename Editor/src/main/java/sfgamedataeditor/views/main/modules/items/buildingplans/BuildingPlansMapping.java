package sfgamedataeditor.views.main.modules.items.buildingplans;

import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.util.HashMap;
import java.util.Map;

public enum BuildingPlansMapping {
    INSTANCE;

    private final Map<String, String> buildingRaceTypeToNameMapping = new HashMap<>();

    BuildingPlansMapping() {
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

    public String getRaceMappingValue(String value) {
        return buildingRaceTypeToNameMapping.get(value);
    }
}
