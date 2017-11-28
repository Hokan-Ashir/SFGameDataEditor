package sfgamedataeditor.database.creatures.corpseloot;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import org.apache.log4j.Logger;
import sfgamedataeditor.database.common.*;
import sfgamedataeditor.database.creatures.equipment.CreatureEquipmentTableService;
import sfgamedataeditor.views.utility.Pair;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public enum CreatureCorpseLootTableService implements TableCreationService {
    INSTANCE {

        private Serializer serializer = new DefaultSerializer();

        @Override
        public void createTable() {
            CommonTableService.INSTANCE.recreateTable(CreatureCorpseLootObject.class);
        }

        @Override
        public void addRecordsToTable(List<Pair<byte[], Long>> offsettedData) {
            CommonTableService.INSTANCE.addRecordsToTable(CreatureCorpseLootObject.class, offsettedData, serializer);
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
            return new Pair<>(0x03F7E10A, 0x03F81B79);
        }

        @Override
        public Class<? extends OffsetableObject> getDTOClass() {
            return CreatureCorpseLootObject.class;
        }
    };

    private static final Logger LOGGER = Logger.getLogger(CreatureEquipmentTableService.class);

    public List<CreatureCorpseLootObject> getCreatureCorpseLootByCreatureId(int creatureId) {
        ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();
        final Dao<CreatureCorpseLootObject, String> dao;
        try {
            dao = DaoManager.createDao(connectionSource, CreatureCorpseLootObject.class);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }

        try {
            List<CreatureCorpseLootObject> objects = dao.queryBuilder().where().eq("creatureId", creatureId).query();
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
