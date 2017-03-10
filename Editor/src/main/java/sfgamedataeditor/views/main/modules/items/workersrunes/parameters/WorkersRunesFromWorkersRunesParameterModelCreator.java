package sfgamedataeditor.views.main.modules.items.workersrunes.parameters;


import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.mvc.ModelCreator;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.common.ModulesModel;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.util.HashMap;
import java.util.Map;

public class WorkersRunesFromWorkersRunesParameterModelCreator implements ModelCreator<ModulesModel, WorkersRunesParametersModel> {

    private final Map<String, String> runeWorkersRaceTypeToNameMapping = new HashMap<>();

    public WorkersRunesFromWorkersRunesParameterModelCreator() {
        initializeUnitRaceTypeToNameMapping();
    }

    private void initializeUnitRaceTypeToNameMapping() {
        addMapping(runeWorkersRaceTypeToNameMapping, "races.humans", "items.rune.workers.in.inventory.humans");
        addMapping(runeWorkersRaceTypeToNameMapping, "races.elves", "items.rune.workers.in.inventory.elves");
        addMapping(runeWorkersRaceTypeToNameMapping, "races.dwarves", "items.rune.workers.in.inventory.dwarves");
        addMapping(runeWorkersRaceTypeToNameMapping, "races.orcs", "items.rune.workers.in.inventory.orcs");
        addMapping(runeWorkersRaceTypeToNameMapping, "races.trolls", "items.rune.workers.in.inventory.trolls");
        addMapping(runeWorkersRaceTypeToNameMapping, "races.dark.elves", "items.rune.workers.in.inventory.dark.elves");
    }

    private void addMapping(Map<String, String> map, String raceI18nKey, String itemTypeI18nKey) {
        map.put(I18NService.INSTANCE.getMessage(I18NTypes.ITEM_TYPES_NAME_MAPPING, itemTypeI18nKey), I18NService.INSTANCE.getMessage(I18NTypes.COMMON, raceI18nKey));
    }

    @Override
    public ModulesModel createModel(WorkersRunesParametersModel childModel) {
        String runeName = childModel.getParameter().getRuneName();
        Integer itemId = ItemPriceParametersTableService.INSTANCE.getItemIdByItemName(runeName);
        int itemType = ItemPriceParametersTableService.INSTANCE.getItemTypeIdByItemId(itemId);
        String raceName = runeWorkersRaceTypeToNameMapping.get(String.valueOf(itemType));
        ModuleParameter parameter = new ModuleParameter(raceName);
        return new ModulesModel(parameter);
    }
}
