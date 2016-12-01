package sfgamedataeditor.database.creatures.spells;

import sfgamedataeditor.database.common.CommonTableService;
import sfgamedataeditor.views.utility.Pair;

import java.util.List;

public enum  CreatureSpellTableService {
    INSTANCE;

    public void createCreatureSpellParametersTable() {
        CommonTableService.INSTANCE.recreateTable(CreatureSpellObject.class);
    }

    public void addRecordsToCreatureSpellParametersTable(List<Pair<byte[], Long>> offsettedData) {
        CommonTableService.INSTANCE.addRecordsToTable(CreatureSpellObject.class, offsettedData);
    }
}
