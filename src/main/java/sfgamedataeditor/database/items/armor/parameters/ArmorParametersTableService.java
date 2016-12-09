package sfgamedataeditor.database.items.armor.parameters;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import org.apache.log4j.Logger;
import sfgamedataeditor.database.common.CommonTableService;
import sfgamedataeditor.views.utility.Pair;

import java.sql.SQLException;
import java.util.List;

public enum ArmorParametersTableService {
    INSTANCE;

    private static final Logger LOGGER = Logger.getLogger(ArmorParametersTableService.class);

    public void createArmorParametersTable() {
        CommonTableService.INSTANCE.recreateTable(ArmorParametersObject.class);
    }

    public void addRecordsToArmorParametersTable(List<Pair<byte[], Long>> offsettedData) {
        CommonTableService.INSTANCE.addRecordsToTable(ArmorParametersObject.class, offsettedData);
    }

    public ArmorParametersObject getObjectByItemId(int itemId) {
        ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();
        final Dao<ArmorParametersObject, String> dao;
        try {
            dao = DaoManager.createDao(connectionSource, ArmorParametersObject.class);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }

        try {
            List<ArmorParametersObject> objects = dao.queryBuilder().where().eq("itemId", itemId).query();
            return objects.get(0);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }
}
