package sfgamedataeditor.database.creatures.corpseloot;

import org.apache.log4j.Logger;
import sfgamedataeditor.database.common.CommonTableService;
import sfgamedataeditor.views.utility.Pair;

import java.util.List;

public enum CreatureCorpseLootTableService {
    INSTANCE;

    private static final Logger LOGGER = Logger.getLogger(CreatureCorpseLootTableService.class);

    public void createCreatureCorpseLootParametersTable() {
        CommonTableService.INSTANCE.recreateTable(CreatureCorpseLootObject.class);
    }

    public void addRecordsToCreatureCorpseLootParametersTable(List<Pair<byte[], Long>> offsettedData) {
        CommonTableService.INSTANCE.addRecordsToTable(CreatureCorpseLootObject.class, offsettedData);
    }
}
