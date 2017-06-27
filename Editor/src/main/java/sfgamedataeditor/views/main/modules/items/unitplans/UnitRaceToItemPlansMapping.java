package sfgamedataeditor.views.main.modules.items.unitplans;

import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.util.Map;
import java.util.TreeMap;

public enum UnitRaceToItemPlansMapping {
    INSTANCE;
    
    public final Map<String, Integer> mappings = new TreeMap<>();

    UnitRaceToItemPlansMapping() {
        putObjectIntoMap("races.humans", "items.unit.plan.in.inventory.humans");
        putObjectIntoMap( "races.elves", "items.unit.plan.in.inventory.elves");
        putObjectIntoMap("races.dwarves", "items.unit.plan.in.inventory.dwarves");
        putObjectIntoMap("races.orcs", "items.unit.plan.in.inventory.orcs");
        putObjectIntoMap("races.trolls", "items.unit.plan.in.inventory.trolls");
        putObjectIntoMap("races.dark.elves", "items.unit.plan.in.inventory.dark.elves");
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

    public Integer[] getTypes() {
        return mappings.values().toArray(new Integer[mappings.size()]);
    }
}
