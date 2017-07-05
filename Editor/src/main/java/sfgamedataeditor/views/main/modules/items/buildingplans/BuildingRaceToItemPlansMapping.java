package sfgamedataeditor.views.main.modules.items.buildingplans;

import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.util.Map;
import java.util.TreeMap;

public enum BuildingRaceToItemPlansMapping {
    INSTANCE;

    public final Map<String, Integer> mappings = new TreeMap<>();

    BuildingRaceToItemPlansMapping() {
        putObjectIntoMap("races.humans", "items.building.plan.in.inventory.humans");
        putObjectIntoMap( "races.elves", "items.building.plan.in.inventory.elves");
        putObjectIntoMap("races.dwarves", "items.building.plan.in.inventory.dwarves");
        putObjectIntoMap("races.orcs", "items.building.plan.in.inventory.orcs");
        putObjectIntoMap("races.trolls", "items.building.plan.in.inventory.trolls");
        putObjectIntoMap("races.dark.elves", "items.building.plan.in.inventory.dark.elves");
    }
    
    void putObjectIntoMap(String raceI8nKey, String itemTypeId) {
        mappings.put(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, raceI8nKey), Integer.valueOf(I18NService.INSTANCE.getMessage(I18NTypes.ITEM_TYPES_NAME_MAPPING, itemTypeId)));
    }

    public String getRaceNameByItemType(int itemType) {
        for (Map.Entry<String, Integer> entry : mappings.entrySet()) {
            if (entry.getValue().equals(itemType)) {
                return entry.getKey();
            }
        }

        return null;
    }

    public Integer getRaceItemType(String raceName) {
        return mappings.get(raceName);
    }

    public Integer[] getTypes() {
        return mappings.values().toArray(new Integer[mappings.size()]);
    }
}
