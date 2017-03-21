package sfgamedataeditor.database.creatures.equipment;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import org.apache.log4j.Logger;
import sfgamedataeditor.database.common.CommonTableService;
import sfgamedataeditor.database.common.TableCreationService;
import sfgamedataeditor.views.utility.Pair;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public enum  CreatureEquipmentTableService implements TableCreationService {
    INSTANCE {
        @Override
        public void createTable() {
            CommonTableService.INSTANCE.recreateTable(CreatureEquipmentObject.class);
        }

        @Override
        public void addRecordsToTable(List<Pair<byte[], Long>> offsettedData) {
            CommonTableService.INSTANCE.addRecordsToTable(CreatureEquipmentObject.class, offsettedData);
        }

        @Override
        public int getDataLength() {
            return 0x5;
        }

        @Override
        public Pair<Integer, Integer> getOffsetInterval() {
            return new Pair<>(0x03F75C32, 0x03F7CF86);
        }
    };

    private static final Logger LOGGER = Logger.getLogger(CreatureEquipmentTableService.class);

    public List<CreatureEquipmentObject> getCreatureEquipmentByCreatureId(int creatureId) {
            ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();
            final Dao<CreatureEquipmentObject, String> dao;
            try {
                dao = DaoManager.createDao(connectionSource, CreatureEquipmentObject.class);
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
                return Collections.emptyList();
            }

            try {
                List<CreatureEquipmentObject> objects = dao.queryBuilder().where().eq("creatureId", creatureId).query();
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
