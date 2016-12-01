package sfgamedataeditor.database.creatures.equipment;

import org.apache.log4j.Logger;
import sfgamedataeditor.database.common.CommonTableService;
import sfgamedataeditor.views.utility.Pair;

import java.util.List;

public enum  CreatureEquipmentTableService {
    INSTANCE;

    private static final Logger LOGGER = Logger.getLogger(CreatureEquipmentTableService.class);

    public void createCreatureEquipmentParametersTable() {
        CommonTableService.INSTANCE.recreateTable(CreatureEquipmentObject.class);
    }

    public void addRecordsToCreatureEquipmentParametersTable(List<Pair<byte[], Long>> offsettedData) {
        CommonTableService.INSTANCE.addRecordsToTable(CreatureEquipmentObject.class, offsettedData);
    }
}
