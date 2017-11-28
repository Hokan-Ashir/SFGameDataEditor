package sfgamedataeditor.database.creatures.parameters;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.dao.RawRowMapper;
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
import java.util.ResourceBundle;

public enum CreatureParametersTableService implements TableCreationService {
    INSTANCE {
        private Serializer serializer = new DefaultSerializer();

        @Override
        public void createTable() {
            CommonTableService.INSTANCE.recreateTable(CreatureParameterObject.class);
        }

        @Override
        public void addRecordsToTable(List<Pair<byte[], Long>> offsettedData) {
            CommonTableService.INSTANCE.addRecordsToTable(CreatureParameterObject.class, offsettedData, serializer);
        }

        @Override
        public byte[] serializeObject(OffsetableObject object) {
            return ObjectDataMappingService.INSTANCE.serializeObject(object, serializer);
        }

        @Override
        public int getDataLength() {
            return 47;
        }

        @Override
        public Pair<Integer, Integer> getOffsetInterval() {
            return new Pair<>(0x0004421D, 0x000613B4);
        }

        @Override
        public Class<? extends OffsetableObject> getDTOClass() {
            return CreatureParameterObject.class;
        }
    };

    private static final Logger LOGGER = Logger.getLogger(CreatureParametersTableService.class);

    public List<ObjectTuple> getListOfCreatureRaces() {
        List<ObjectTuple> result = new ArrayList<>();
        ResourceBundle bundle = I18NService.INSTANCE.getBundle(I18NTypes.RACES);
        for (String key : bundle.keySet()) {
            result.add(new ObjectTuple(bundle.getString(key), Integer.valueOf(key)));
        }
        Collections.sort(result);

        return result;
    }

    public CreatureParameterObject getCreatureParameterObjectByCreatureName(Integer creatureId) {
        ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();
        Dao<CreatureParameterObject, ?> dao;
        try {
            dao = DaoManager.createDao(connectionSource, CreatureParameterObject.class);
            GenericRawResults<CreatureParameterObject> rawResults =
                    dao.queryRaw(
                            "select creature_parameters.* from creature_parameters inner join creature_common_parameters on creature_common_parameters.statsId = creature_parameters.statsId where creature_common_parameters.creatureId = ?",
                            dao.getRawRowMapper(),
                            String.valueOf(creatureId));

            return rawResults.getResults().get(0);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    public Integer getRaceIdByCreatureName(Integer creatureId) {
        ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();
        final Dao<CreatureParameterObject, Integer> parametersObject;
        try {
            parametersObject = DaoManager.createDao(connectionSource, CreatureParameterObject.class);

            GenericRawResults<String> rawResults =
                    parametersObject.queryRaw(
                            "select creature_parameters.raceId from creature_common_parameters inner join creature_parameters on creature_common_parameters.statsId = creature_parameters.statsId where creature_common_parameters.creatureId = ?",
                            new RawRowMapper<String>() {
                                public String mapRow(String[] columnNames,
                                                     String[] resultColumns) {
                                    return resultColumns[0];
                                }
                            },
                            String.valueOf(creatureId));

            return Integer.valueOf(rawResults.getResults().get(0));
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return 0;
        }
    }

    public CreatureParameterObject getCreatureObjectByStatsId(int statsId) {
        ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();
        Dao<CreatureParameterObject, ?> dao;
        try {
            dao = DaoManager.createDao(connectionSource, CreatureParameterObject.class);
            List<CreatureParameterObject> creatureParameterObjects = dao.queryBuilder().where().eq("statsId", statsId).query();
            return creatureParameterObjects.get(0);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }
}
