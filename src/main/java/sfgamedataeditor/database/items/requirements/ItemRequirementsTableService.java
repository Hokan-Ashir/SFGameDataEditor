package sfgamedataeditor.database.items.requirements;

import sfgamedataeditor.database.common.CommonTableService;
import sfgamedataeditor.views.utility.Pair;

import java.util.List;

public enum ItemRequirementsTableService {
    INSTANCE;

    public void createItemRequirementsTable() {
        CommonTableService.INSTANCE.recreateTable(ItemRequirementsObject.class);
    }

    public void addRecordsToItemRequirementsTable(List<Pair<byte[], Long>> offsettedData) {
        CommonTableService.INSTANCE.addRecordsToTable(ItemRequirementsObject.class, offsettedData);
    }
}
