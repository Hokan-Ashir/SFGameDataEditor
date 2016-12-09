package sfgamedataeditor.database.items.requirements;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import org.apache.log4j.Logger;
import sfgamedataeditor.database.common.CommonTableService;
import sfgamedataeditor.views.utility.Pair;

import java.sql.SQLException;
import java.util.List;

public enum ItemRequirementsTableService {
    INSTANCE;

    private static final Logger LOGGER = Logger.getLogger(ItemRequirementsTableService.class);

    public void createItemRequirementsTable() {
        CommonTableService.INSTANCE.recreateTable(ItemRequirementsObject.class);
    }

    public void addRecordsToItemRequirementsTable(List<Pair<byte[], Long>> offsettedData) {
        CommonTableService.INSTANCE.addRecordsToTable(ItemRequirementsObject.class, offsettedData);
    }

    public ItemRequirementsObject getObjectByItemId(int itemId) {
        ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();
        final Dao<ItemRequirementsObject, String> dao;
        try {
            dao = DaoManager.createDao(connectionSource, ItemRequirementsObject.class);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }

        try {
            List<ItemRequirementsObject> objects = dao.queryBuilder().where().eq("itemId", itemId).query();
            return objects.get(0);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }
}
