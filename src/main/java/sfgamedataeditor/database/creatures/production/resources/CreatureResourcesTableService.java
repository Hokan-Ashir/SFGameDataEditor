package sfgamedataeditor.database.creatures.production.resources;

import sfgamedataeditor.database.common.CommonTableService;
import sfgamedataeditor.views.utility.Pair;

import java.util.List;

public enum CreatureResourcesTableService {
    INSTANCE;

    public void createCreatureResourcesParametersTable() {
        CommonTableService.INSTANCE.recreateTable(CreatureResourcesObject.class);
    }

    public void addRecordsToCreatureResourcesParametersTable(List<Pair<byte[], Long>> offsettedData) {
        CommonTableService.INSTANCE.addRecordsToTable(CreatureResourcesObject.class, offsettedData);
    }
}
