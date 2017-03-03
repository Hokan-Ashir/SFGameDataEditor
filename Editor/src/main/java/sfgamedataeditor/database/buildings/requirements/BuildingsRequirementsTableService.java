package sfgamedataeditor.database.buildings.requirements;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import org.apache.log4j.Logger;
import sfgamedataeditor.database.common.CommonTableService;
import sfgamedataeditor.database.common.TableCreationService;
import sfgamedataeditor.dataextraction.DTOOffsetTypes;
import sfgamedataeditor.views.utility.Pair;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public enum BuildingsRequirementsTableService implements TableCreationService {
    INSTANCE {
        @Override
        public void createTable() {
            CommonTableService.INSTANCE.recreateTable(BuildingsRequirementsObject.class);
        }

        @Override
        public void addRecordsToTable(List<Pair<byte[], Long>> offsettedData) {
            CommonTableService.INSTANCE.addRecordsToTable(BuildingsRequirementsObject.class, offsettedData);
        }

        @Override
        public DTOOffsetTypes getDTOOffsetType() {
            return DTOOffsetTypes.BUILDINGS_REQUIREMENTS;
        }
    };

    private static final Logger LOGGER = Logger.getLogger(BuildingsRequirementsTableService.class);

    public List<BuildingsRequirementsObject> getBuildingRequirementsObjectsByBuildingId(Integer buildingId) {
        ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();
        final Dao<BuildingsRequirementsObject, Integer> dao;
        try {
            dao = DaoManager.createDao(connectionSource, BuildingsRequirementsObject.class);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }

        try {
            List<BuildingsRequirementsObject> objectList = dao.queryBuilder().where().eq("buildingId", buildingId).query();
            if (objectList.isEmpty()) {
                return Collections.emptyList();
            } else {
                return objectList;
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }
}
