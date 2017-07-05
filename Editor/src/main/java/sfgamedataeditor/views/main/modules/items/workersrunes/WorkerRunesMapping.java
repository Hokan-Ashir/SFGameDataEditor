package sfgamedataeditor.views.main.modules.items.workersrunes;

import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.util.HashMap;
import java.util.Map;

public enum WorkerRunesMapping {
    INSTANCE;

    private final Map<String, String> runeWorkersRaceTypeToNameMapping = new HashMap<>();

    WorkerRunesMapping() {
        addMapping(runeWorkersRaceTypeToNameMapping, "races.humans", "items.rune.workers.in.inventory.humans");
        addMapping(runeWorkersRaceTypeToNameMapping, "races.elves", "items.rune.workers.in.inventory.elves");
        addMapping(runeWorkersRaceTypeToNameMapping, "races.dwarves", "items.rune.workers.in.inventory.dwarves");
        addMapping(runeWorkersRaceTypeToNameMapping, "races.orcs", "items.rune.workers.in.inventory.orcs");
        addMapping(runeWorkersRaceTypeToNameMapping, "races.trolls", "items.rune.workers.in.inventory.trolls");
        addMapping(runeWorkersRaceTypeToNameMapping, "races.dark.elves", "items.rune.workers.in.inventory.dark.elves");
    }

    private void addMapping(Map<String, String> map, String raceI18nKey, String itemTypeI18nKey) {
        map.put(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, raceI18nKey), I18NService.INSTANCE.getMessage(I18NTypes.ITEM_TYPES_NAME_MAPPING, itemTypeI18nKey));
    }

    public Integer getWorkerRuneTypeByName(String value) {
        return Integer.valueOf(runeWorkersRaceTypeToNameMapping.get(value));
    }

    public String getWorkerRuneRaceName(Integer itemType) {
        String typeId = String.valueOf(itemType);
        for (Map.Entry<String, String> entry : runeWorkersRaceTypeToNameMapping.entrySet()) {
            if (entry.getValue().equals(typeId)) {
                return entry.getKey();
            }
        }

        return null;
    }
}
