package sfgamedataeditor.database.items.effects;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import org.apache.log4j.Logger;
import sfgamedataeditor.database.common.CommonTableService;
import sfgamedataeditor.database.common.OffsetableObject;
import sfgamedataeditor.database.common.TableCreationService;
import sfgamedataeditor.views.utility.Pair;

import java.sql.SQLException;
import java.util.List;

public enum ItemEffectsTableService implements TableCreationService {
    INSTANCE {
        @Override
        public void createTable() {
            CommonTableService.INSTANCE.recreateTable(ItemEffectsObject.class);
        }

        @Override
        public void addRecordsToTable(List<Pair<byte[], Long>> offsettedData) {
            CommonTableService.INSTANCE.addRecordsToTable(ItemEffectsObject.class, offsettedData);
        }

        @Override
        public int getDataLength() {
            return 0x4;
        }

        @Override
        public Pair<Integer, Integer> getOffsetInterval() {
            return new Pair<>(0x0012B373, 0x0012D16A);
        }

        @Override
        public Class<? extends OffsetableObject> getDTOClass() {
            return ItemEffectsObject.class;
        }
    };

    private static final Logger LOGGER = Logger.getLogger(ItemEffectsTableService.class);

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
            if (objects.isEmpty()) {
                return null;
            }

            return objects.get(0);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }
}
