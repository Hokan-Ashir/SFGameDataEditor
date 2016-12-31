package sfgamedataeditor.database.creatures.corpseloot;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import org.apache.log4j.Logger;
import sfgamedataeditor.database.common.CommonTableService;
import sfgamedataeditor.database.common.TableCreationService;
import sfgamedataeditor.database.creatures.equipment.CreatureEquipmentTableService;
import sfgamedataeditor.dataextraction.DTOOffsetTypes;
import sfgamedataeditor.views.utility.Pair;

import java.sql.SQLException;
import java.util.List;

public enum CreatureCorpseLootTableService implements TableCreationService {
    INSTANCE {
        @Override
        public void createTable() {
            CommonTableService.INSTANCE.recreateTable(CreatureCorpseLootObject.class);
        }

        @Override
        public void addRecordsToTable(List<Pair<byte[], Long>> offsettedData) {
            CommonTableService.INSTANCE.addRecordsToTable(CreatureCorpseLootObject.class, offsettedData);
        }

        @Override
        public DTOOffsetTypes getDTOOffsetType() {
            return DTOOffsetTypes.CREATURE_CORPSE_LOOT;
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
            return null;
        }

        try {
            List<CreatureCorpseLootObject> objects = dao.queryBuilder().where().eq("creatureId", creatureId).query();
            if (objects.isEmpty()) {
                return null;
            } else {
                return objects;
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }
}
