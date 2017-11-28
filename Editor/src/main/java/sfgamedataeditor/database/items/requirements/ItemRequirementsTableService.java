package sfgamedataeditor.database.items.requirements;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import org.apache.log4j.Logger;
import sfgamedataeditor.database.common.*;
import sfgamedataeditor.views.utility.Pair;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public enum ItemRequirementsTableService implements TableCreationService {
    INSTANCE {
        private Serializer serializer = new DefaultSerializer();

        @Override
        public void createTable() {
            CommonTableService.INSTANCE.recreateTable(ItemRequirementsObject.class);
        }

        @Override
        public void addRecordsToTable(List<Pair<byte[], Long>> offsettedData) {
            CommonTableService.INSTANCE.addRecordsToTable(ItemRequirementsObject.class, offsettedData, serializer);
        }

        @Override
        public byte[] serializeObject(OffsetableObject object) {
            return ObjectDataMappingService.INSTANCE.serializeObject(object, serializer);
        }

        @Override
        public int getDataLength() {
            return 0x6;
        }

        @Override
        public Pair<Integer, Integer> getOffsetInterval() {
            return new Pair<>(0x00094450, 0x0009A485);
        }

        @Override
        public Class<? extends OffsetableObject> getDTOClass() {
            return ItemRequirementsObject.class;
        }
    };

    private static final Logger LOGGER = Logger.getLogger(ItemRequirementsTableService.class);

    public List<ItemRequirementsObject> getObjectsByItemId(int itemId) {
        ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();
        final Dao<ItemRequirementsObject, String> dao;
        try {
            dao = DaoManager.createDao(connectionSource, ItemRequirementsObject.class);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }

        try {
            return dao.queryBuilder().where().eq("itemId", itemId).query();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }
}
