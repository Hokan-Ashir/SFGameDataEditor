package sfgamedataeditor.database.items.effects;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import org.apache.log4j.Logger;
import sfgamedataeditor.database.common.CommonTableService;
import sfgamedataeditor.views.utility.Pair;

import java.sql.SQLException;
import java.util.List;

public enum ItemEffectsTableService {
    INSTANCE;

    private static final Logger LOGGER = Logger.getLogger(ItemEffectsTableService.class);

    public void createItemEffectsTable() {
        CommonTableService.INSTANCE.recreateTable(ItemEffectsObject.class);
    }

    public void addRecordsToItemEffectsTable(List<Pair<byte[], Long>> offsettedData) {
        CommonTableService.INSTANCE.addRecordsToTable(ItemEffectsObject.class, offsettedData);
    }

    public ItemEffectsObject getObjectByItemId(int itemId) {
        ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();
        final Dao<ItemEffectsObject, String> dao;
        try {
            dao = DaoManager.createDao(connectionSource, ItemEffectsObject.class);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }

        try {
            List<ItemEffectsObject> objects = dao.queryBuilder().where().eq("itemId", itemId).query();
            // item has NO effects, i.e. most of items except weapons
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
