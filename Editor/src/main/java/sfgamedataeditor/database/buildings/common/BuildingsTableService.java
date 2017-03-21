package sfgamedataeditor.database.buildings.common;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import org.apache.log4j.Logger;
import sfgamedataeditor.database.common.CommonTableService;
import sfgamedataeditor.database.common.OffsetableObject;
import sfgamedataeditor.database.common.TableCreationService;
import sfgamedataeditor.views.utility.Pair;
import sfgamedataeditor.views.utility.ViewTools;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public enum BuildingsTableService implements TableCreationService {
    INSTANCE {
        @Override
        public void createTable() {
            CommonTableService.INSTANCE.recreateTable(BuildingsObject.class);
        }

        @Override
        public void addRecordsToTable(List<Pair<byte[], Long>> offsettedData) {
            CommonTableService.INSTANCE.addRecordsToTable(BuildingsObject.class, offsettedData);
        }

        @Override
        public int getDataLength() {
            return 0x17;
        }

        @Override
        public Pair<Integer, Integer> getOffsetInterval() {
            return new Pair<>(0x03F81C69, 0x03F82F01);
        }

        @Override
        public Class<? extends OffsetableObject> getDTOClass() {
            return BuildingsObject.class;
        }
    };

    private static final Logger LOGGER = Logger.getLogger(BuildingsTableService.class);

    public Set<String> getBuildingsRacesNames() {
        ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();
        final Dao<BuildingsObject, Integer> dao;
        try {
            dao = DaoManager.createDao(connectionSource, BuildingsObject.class);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptySet();
        }

        try {
            List<BuildingsObject> objects = dao.queryBuilder().selectColumns("raceId").groupBy("raceId").query();
            if (objects.isEmpty()) {
                return Collections.emptySet();
            } else {
                Set<String> result = new TreeSet<>();
                for (BuildingsObject object : objects) {
                    result.add(I18NService.INSTANCE.getMessage(I18NTypes.RACES, String.valueOf(object.raceId)));
                }

                return result;
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptySet();
        }
    }

    public Set<String> getBuildingsNamesByRaceName(String raceName) {
        int raceId = ViewTools.getKeyByPropertyValue(raceName, I18NTypes.RACES);
        return getBuildingsNamesByRaceId(raceId);
    }

    public Set<String> getBuildingsNamesByRaceId(Integer raceId) {
        ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();
        final Dao<BuildingsObject, Integer> dao;
        try {
            dao = DaoManager.createDao(connectionSource, BuildingsObject.class);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptySet();
        }

        try {
            List<BuildingsObject> objects = dao.queryBuilder().selectColumns("buildingId").where().eq("raceId", raceId).query();
            if (objects.isEmpty()) {
                return Collections.emptySet();
            } else {
                Set<String> result = new TreeSet<>();
                for (BuildingsObject object : objects) {
                    result.add(I18NService.INSTANCE.getMessage(I18NTypes.BUILDING_NAMES_MAPPING, String.valueOf(object.buildingId)));
                }

                return result;
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptySet();
        }
    }

    public Integer getRaceIdByBuildingName(String buildingName) {
        int buildingId = ViewTools.getKeyByPropertyValue(buildingName, I18NTypes.BUILDING_NAMES_MAPPING);
        return getBuildingObjectByBuildingId(buildingId).raceId;
    }

    public BuildingsObject getBuildingObjectByBuildingId(Integer buildingId) {
        ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();
        final Dao<BuildingsObject, Integer> dao;
        try {
            dao = DaoManager.createDao(connectionSource, BuildingsObject.class);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }

        try {
            return dao.queryBuilder().where().eq("buildingId", buildingId).query().get(0);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }
}
