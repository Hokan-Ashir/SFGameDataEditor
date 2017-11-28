package sfgamedataeditor.database.objects.chests;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import org.apache.log4j.Logger;
import sfgamedataeditor.database.common.*;
import sfgamedataeditor.views.common.ObjectTuple;
import sfgamedataeditor.views.utility.Pair;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum ChestCorpseLootTableService implements TableCreationService {
    INSTANCE {
        private Serializer serializer = new DefaultSerializer();


        @Override
        public void createTable() {
            CommonTableService.INSTANCE.recreateTable(ChestCorpseLootObject.class);
        }

        @Override
        public void addRecordsToTable(List<Pair<byte[], Long>> offsettedData) {
            CommonTableService.INSTANCE.addRecordsToTable(ChestCorpseLootObject.class, offsettedData, serializer);
        }

        @Override
        public byte[] serializeObject(OffsetableObject object) {
            return ObjectDataMappingService.INSTANCE.serializeObject(object, serializer);
        }

        @Override
        public int getDataLength() {
            return 11;
        }

        @Override
        public Pair<Integer, Integer> getOffsetInterval() {
            return new Pair<>(0x03FA5C53, 0x03FAB489);
        }

        @Override
        public Class<? extends OffsetableObject> getDTOClass() {
            return ChestCorpseLootObject.class;
        }
    };

    private static final Logger LOGGER = Logger.getLogger(ChestCorpseLootTableService.class);

    public List<ObjectTuple> getAllChestCorpseLootObjectNames() {
        List<ChestCorpseLootObject> allTableData = CommonTableService.INSTANCE.getAllTableData(ChestCorpseLootObject.class);
        List<ObjectTuple> result = new ArrayList<>();
        for (ChestCorpseLootObject chestCorpseLootObject : allTableData) {
            String objectName = I18NService.INSTANCE.getMessage(I18NTypes.OBJECTS, String.valueOf(chestCorpseLootObject.chestCorpseId));
            result.add(new ObjectTuple(objectName, chestCorpseLootObject.chestCorpseId));
        }

        return result;
    }

    public List<ChestCorpseLootObject> getChestLootObjectsById(Integer chestLootId) {
        ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();
        final Dao<ChestCorpseLootObject, String> dao;
        try {
            dao = DaoManager.createDao(connectionSource, ChestCorpseLootObject.class);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }

        try {
            List<ChestCorpseLootObject> objects = dao.queryBuilder().where().eq("chestCorpseId", chestLootId).query();
            if (objects.isEmpty()) {
                return Collections.emptyList();
            } else {
                return objects;
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }
}
