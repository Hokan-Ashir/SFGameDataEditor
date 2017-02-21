package sfgamedataeditor.database.buildings.army.requirements;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import org.apache.log4j.Logger;
import sfgamedataeditor.database.common.CommonTableService;
import sfgamedataeditor.database.common.TableCreationService;
import sfgamedataeditor.dataextraction.DTOOffsetTypes;
import sfgamedataeditor.views.utility.Pair;
import sfgamedataeditor.views.utility.ViewTools;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public enum BuildingsArmyRequirementsTableService implements TableCreationService {
    INSTANCE {
        @Override
        public void createTable() {
            CommonTableService.INSTANCE.recreateTable(BuildingsArmyRequirementsObject.class);
        }

        @Override
        public void addRecordsToTable(List<Pair<byte[], Long>> offsettedData) {
            CommonTableService.INSTANCE.addRecordsToTable(BuildingsArmyRequirementsObject.class, offsettedData);
        }

        @Override
        public DTOOffsetTypes getDTOOffsetType() {
            return DTOOffsetTypes.BUILDINGS_ARMY_REQUIREMENTS;
        }
    };

    private static final Logger LOGGER = Logger.getLogger(BuildingsArmyRequirementsTableService.class);

    public List<BuildingsArmyRequirementsObject> getBuildingArmyObjectByBuildingName(String buildingName) {
        ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();
        final Dao<BuildingsArmyRequirementsObject, Integer> dao;
        try {
            dao = DaoManager.createDao(connectionSource, BuildingsArmyRequirementsObject.class);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }

        try {
            int buildingId = ViewTools.getKeyByPropertyValue(buildingName, I18NTypes.BUILDING_NAMES_MAPPING);
            return dao.queryBuilder().where().eq("buildingId", buildingId).query();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }
}
