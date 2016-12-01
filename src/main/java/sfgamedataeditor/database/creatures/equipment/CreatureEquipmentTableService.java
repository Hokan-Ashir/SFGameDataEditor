package sfgamedataeditor.database.creatures.equipment;

import sfgamedataeditor.database.common.CommonTableService;
import sfgamedataeditor.views.utility.Pair;

import java.util.List;

public enum  CreatureEquipmentTableService {
    INSTANCE;

    public void createCreatureEquipmentParametersTable() {
        CommonTableService.INSTANCE.recreateTable(CreatureEquipmentObject.class);
    }

    public void addRecordsToCreatureEquipmentParametersTable(List<Pair<byte[], Long>> offsettedData) {
        CommonTableService.INSTANCE.addRecordsToTable(CreatureEquipmentObject.class, offsettedData);
    }
}
