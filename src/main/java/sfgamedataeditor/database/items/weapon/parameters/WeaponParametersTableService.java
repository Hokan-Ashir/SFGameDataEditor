package sfgamedataeditor.database.items.weapon.parameters;

import sfgamedataeditor.database.common.CommonTableService;
import sfgamedataeditor.views.utility.Pair;

import java.util.List;

public enum WeaponParametersTableService {
    INSTANCE;

    public void createWeaponParametersTable() {
        CommonTableService.INSTANCE.recreateTable(WeaponParametersObject.class);
    }

    public void addRecordsToWeaponParametersTable(List<Pair<byte[], Long>> offsettedData) {
        CommonTableService.INSTANCE.addRecordsToTable(WeaponParametersObject.class, offsettedData);
    }
}
