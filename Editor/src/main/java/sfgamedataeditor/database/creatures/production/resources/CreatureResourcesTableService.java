package sfgamedataeditor.database.creatures.production.resources;

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
import java.util.Collections;
import java.util.List;

public enum CreatureResourcesTableService implements TableCreationService {
    INSTANCE {
        @Override
        public void createTable() {
            CommonTableService.INSTANCE.recreateTable(CreatureResourcesObject.class);
        }

        @Override
        public void addRecordsToTable(List<Pair<byte[], Long>> offsettedData) {
            CommonTableService.INSTANCE.addRecordsToTable(CreatureResourcesObject.class, offsettedData);
        }

        @Override
        public DTOOffsetTypes getDTOOffsetType() {
            return DTOOffsetTypes.CREATURE_RESOURCES;
        }
    };

    private static final Logger LOGGER = Logger.getLogger(CreatureEquipmentTableService.class);

    public List<CreatureResourcesObject> getCreatureResourcesByCreatureId(int creatureId) {
        ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();
        final Dao<CreatureResourcesObject, String> dao;
        try {
            dao = DaoManager.createDao(connectionSource, CreatureResourcesObject.class);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }

        try {
            List<CreatureResourcesObject> objects = dao.queryBuilder().where().eq("creatureId", creatureId).query();
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