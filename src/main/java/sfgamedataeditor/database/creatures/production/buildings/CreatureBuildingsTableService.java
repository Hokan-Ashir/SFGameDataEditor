package sfgamedataeditor.database.creatures.production.buildings;

import sfgamedataeditor.database.common.CommonTableService;
import sfgamedataeditor.views.utility.Pair;

import java.util.List;

public enum CreatureBuildingsTableService {
    INSTANCE;

    public void createCreatureBuildingsParametersTable() {
        CommonTableService.INSTANCE.recreateTable(CreatureBuildingsObject.class);
    }

    public void addRecordsToCreatureBuildingParametersTable(List<Pair<byte[], Long>> offsettedData) {
        CommonTableService.INSTANCE.addRecordsToTable(CreatureBuildingsObject.class, offsettedData);
    }
}
