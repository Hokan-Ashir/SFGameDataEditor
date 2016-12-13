package sfgamedataeditor.database.items.weapon.parameters;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import org.apache.log4j.Logger;
import sfgamedataeditor.database.common.CommonTableService;
import sfgamedataeditor.views.utility.Pair;

import java.sql.SQLException;
import java.util.List;

public enum WeaponParametersTableService {
    INSTANCE;

    private static final Logger LOGGER = Logger.getLogger(WeaponParametersTableService.class);

    public void createWeaponParametersTable() {
        CommonTableService.INSTANCE.recreateTable(WeaponParametersObject.class);
    }

    public void addRecordsToWeaponParametersTable(List<Pair<byte[], Long>> offsettedData) {
        CommonTableService.INSTANCE.addRecordsToTable(WeaponParametersObject.class, offsettedData);
    }

    public WeaponParametersObject getObjectByItemId(int itemId) {
        ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();
        final Dao<WeaponParametersObject, String> dao;
        try {
            dao = DaoManager.createDao(connectionSource, WeaponParametersObject.class);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }

        try {
            List<WeaponParametersObject> objects = dao.queryBuilder().where().eq("itemId", itemId).query();
            if (objects.isEmpty()) {
                return null;
            } else {
                return objects.get(0);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }
}
