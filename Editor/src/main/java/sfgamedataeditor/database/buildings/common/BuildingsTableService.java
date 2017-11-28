package sfgamedataeditor.database.buildings.common;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import org.apache.log4j.Logger;
import sfgamedataeditor.database.common.*;
import sfgamedataeditor.database.text.TextTableService;
import sfgamedataeditor.views.common.ObjectTuple;
import sfgamedataeditor.views.utility.Pair;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum BuildingsTableService implements TableCreationService {
    INSTANCE {

        private Serializer serializer = new DefaultSerializer();

        @Override
        public void createTable() {
            CommonTableService.INSTANCE.recreateTable(BuildingsObject.class);
        }

        @Override
        public void addRecordsToTable(List<Pair<byte[], Long>> offsettedData) {
            CommonTableService.INSTANCE.addRecordsToTable(BuildingsObject.class, offsettedData, serializer);
        }

        @Override
        public byte[] serializeObject(OffsetableObject object) {
            return ObjectDataMappingService.INSTANCE.serializeObject(object, serializer);
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

    public List<ObjectTuple> getBuildingsRacesNames() {
        ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();
        final Dao<BuildingsObject, Integer> dao;
        try {
            dao = DaoManager.createDao(connectionSource, BuildingsObject.class);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }

        try {
            List<BuildingsObject> objects = dao.queryBuilder().selectColumns("raceId").groupBy("raceId").query();
            if (objects.isEmpty()) {
                return Collections.emptyList();
            } else {
                List<ObjectTuple> result = new ArrayList<>();
                for (BuildingsObject object : objects) {
                    String name = I18NService.INSTANCE.getMessage(I18NTypes.RACES, String.valueOf(object.raceId));
                    result.add(new ObjectTuple(name, object.raceId));
                }
                Collections.sort(result);

                return result;
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    public List<ObjectTuple> getBuildingsNamesByRaceId(Integer raceId) {
        ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();
        final Dao<BuildingsObject, Integer> dao;
        try {
            dao = DaoManager.createDao(connectionSource, BuildingsObject.class);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }

        try {
            List<BuildingsObject> objects = dao.queryBuilder().selectColumns("buildingId", "nameId").orderBy("nameId", true).where().eq("raceId", raceId).query();
            if (objects.isEmpty()) {
                return Collections.emptyList();
            } else {
                Integer[] nameIds = new Integer[objects.size()];
                Integer[] objectIds = new Integer[objects.size()];
                for (int i = 0; i < objects.size(); i++) {
                    nameIds[i] = objects.get(i).nameId;
                    objectIds[i] = objects.get(i).buildingId;
                }

                return TextTableService.INSTANCE.getObjectTuples(nameIds, objectIds);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    public Integer getRaceIdByBuildingName(Integer buildingId) {
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
