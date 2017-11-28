package sfgamedataeditor.database.buildings.army.requirements;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import org.apache.log4j.Logger;
import sfgamedataeditor.database.common.*;
import sfgamedataeditor.views.utility.Pair;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public enum BuildingsArmyRequirementsTableService implements TableCreationService {
    INSTANCE {

        private Serializer serializer = new DefaultSerializer();

        @Override
        public void createTable() {
            CommonTableService.INSTANCE.recreateTable(BuildingsArmyRequirementsObject.class);
        }

        @Override
        public void addRecordsToTable(List<Pair<byte[], Long>> offsettedData) {
            CommonTableService.INSTANCE.addRecordsToTable(BuildingsArmyRequirementsObject.class, offsettedData, serializer);
        }

        @Override
        public byte[] serializeObject(OffsetableObject object) {
            return ObjectDataMappingService.INSTANCE.serializeObject(object, serializer);
        }

        @Override
        public int getDataLength() {
            return 0x5;
        }

        @Override
        public Pair<Integer, Integer> getOffsetInterval() {
            return new Pair<>(0x03F81B86, 0x03F81C5C);
        }

        @Override
        public Class<? extends OffsetableObject> getDTOClass() {
            return BuildingsArmyRequirementsObject.class;
        }
    };

    private static final Logger LOGGER = Logger.getLogger(BuildingsArmyRequirementsTableService.class);

    public List<BuildingsArmyRequirementsObject> getBuildingArmyObjectByBuildingId(Integer buildingId) {
        ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();
        final Dao<BuildingsArmyRequirementsObject, Integer> dao;
        try {
            dao = DaoManager.createDao(connectionSource, BuildingsArmyRequirementsObject.class);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }

        try {
            return dao.queryBuilder().where().eq("buildingId", buildingId).query();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }
}
