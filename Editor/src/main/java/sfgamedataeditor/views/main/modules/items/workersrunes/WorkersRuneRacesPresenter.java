package sfgamedataeditor.views.main.modules.items.workersrunes;

import sfgamedataeditor.views.common.AbstractModulesPresenter;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.main.modules.items.workersrunes.parameters.WorkersRunesParametersModel;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class WorkersRuneRacesPresenter extends AbstractModulesPresenter<ModuleParameter, WorkersRuneRacesView, WorkersRunesParametersModel> {

    private final Map<String, String> runeWorkersRaceTypeToNameMapping = new HashMap<>();
    private final WorkerRunesModelCreator modelCreator = new WorkerRunesModelCreator();

    public WorkersRuneRacesPresenter(WorkersRuneRacesView view) {
        super(view);
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
        map.put(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, raceI18nKey), I18NService.INSTANCE.getMessage(I18NTypes.ITEM_TYPES_NAME_MAPPING, itemTypeI18nKey));
    }

    @Override
    protected WorkersRunesParametersModel createModel() {
        String selectedModuleName = getView().getSelectedModuleName();
        Integer itemType = Integer.valueOf(runeWorkersRaceTypeToNameMapping.get(selectedModuleName));
        Icon icon = getView().getSelectedModuleIcon();
        return modelCreator.createModel(itemType, icon);
    }


}
