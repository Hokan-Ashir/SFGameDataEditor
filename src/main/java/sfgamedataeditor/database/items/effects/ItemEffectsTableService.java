package sfgamedataeditor.database.items.effects;

import sfgamedataeditor.database.common.CommonTableService;
import sfgamedataeditor.views.utility.Pair;

import java.util.List;

public enum ItemEffectsTableService {
    INSTANCE;

    public void createItemEffectsTable() {
        CommonTableService.INSTANCE.recreateTable(ItemEffectsObject.class);
    }

    public void addRecordsToItemEffectsTable(List<Pair<byte[], Long>> offsettedData) {
        CommonTableService.INSTANCE.addRecordsToTable(ItemEffectsObject.class, offsettedData);
    }
}
