package sfgamedataeditor.database.creatures.spells;

import org.apache.log4j.Logger;
import sfgamedataeditor.database.common.CommonTableService;
import sfgamedataeditor.views.utility.Pair;

import java.util.List;

public enum  CreatureSpellTableService {
    INSTANCE;

    private static final Logger LOGGER = Logger.getLogger(CreatureSpellTableService.class);

    public void createCreatureSpellParametersTable() {
        CommonTableService.INSTANCE.recreateTable(CreatureSpellObject.class);
    }

    public void addRecordsToCreatureSpellParametersTable(List<Pair<byte[], Long>> offsettedData) {
        CommonTableService.INSTANCE.addRecordsToTable(CreatureSpellObject.class, offsettedData);
    }
}
