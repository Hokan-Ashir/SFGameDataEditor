package sfgamedataeditor.database.items.armor.parameters;

import sfgamedataeditor.database.common.CommonTableService;
import sfgamedataeditor.views.utility.Pair;

import java.util.List;

public enum ArmorParametersTableService {
    INSTANCE;

    public void createArmorParametersTable() {
        CommonTableService.INSTANCE.recreateTable(ArmorParametersObject.class);
    }

    public void addRecordsToArmorParametersTable(List<Pair<byte[], Long>> offsettedData) {
        CommonTableService.INSTANCE.addRecordsToTable(ArmorParametersObject.class, offsettedData);
    }
}
