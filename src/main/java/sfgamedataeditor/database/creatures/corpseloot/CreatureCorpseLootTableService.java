package sfgamedataeditor.database.creatures.corpseloot;

import sfgamedataeditor.database.common.CommonTableService;
import sfgamedataeditor.views.utility.Pair;

import java.util.List;

public enum CreatureCorpseLootTableService {
    INSTANCE;

    public void createCreatureCorpseLootParametersTable() {
        CommonTableService.INSTANCE.recreateTable(CreatureCorpseLootObject.class);
    }

    public void addRecordsToCreatureCorpseLootParametersTable(List<Pair<byte[], Long>> offsettedData) {
        CommonTableService.INSTANCE.addRecordsToTable(CreatureCorpseLootObject.class, offsettedData);
    }
}
