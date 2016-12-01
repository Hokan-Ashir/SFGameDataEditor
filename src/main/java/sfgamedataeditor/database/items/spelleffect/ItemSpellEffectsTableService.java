package sfgamedataeditor.database.items.spelleffect;

import sfgamedataeditor.database.common.CommonTableService;
import sfgamedataeditor.views.utility.Pair;

import java.util.List;

public enum ItemSpellEffectsTableService {
    INSTANCE;

    public void createItemSpellEffectsTable() {
        CommonTableService.INSTANCE.recreateTable(ItemSpellEffectsObject.class);
    }

    public void addRecordsToItemSpellEffectsTable(List<Pair<byte[], Long>> offsettedData) {
        CommonTableService.INSTANCE.addRecordsToTable(ItemSpellEffectsObject.class, offsettedData);
    }
}
