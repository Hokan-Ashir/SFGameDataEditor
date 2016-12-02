package sfgamedataeditor.database.creatures.parameters;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.dao.RawRowMapper;
import com.j256.ormlite.support.ConnectionSource;
import org.apache.log4j.Logger;
import sfgamedataeditor.database.common.CommonTableService;
import sfgamedataeditor.views.utility.Pair;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.sql.SQLException;
import java.util.*;

public enum CreatureParametersTableService {
    INSTANCE;

    private static final Logger LOGGER = Logger.getLogger(CreatureParametersTableService.class);

    public void createCreatureParametersTable() {
        CommonTableService.INSTANCE.recreateTable(CreatureParameterObject.class);
    }

    public void addRecordsToCreatureParametersTable(List<Pair<byte[], Long>> offsettedData) {
        CommonTableService.INSTANCE.addRecordsToTable(CreatureParameterObject.class, offsettedData);
    }

    public Set<String> getListOfCreatureRaces() {
        ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();
        Dao<CreatureParameterObject, ?> dao;
        try {
            dao = DaoManager.createDao(connectionSource, CreatureParameterObject.class);
            List<CreatureParameterObject> raceIds = dao.queryBuilder().selectColumns("raceId").query();

            Set<String> creatureRaces = new HashSet<>();
            for (CreatureParameterObject raceId : raceIds) {
                try {
                    creatureRaces.add(I18NService.INSTANCE.getMessage(I18NTypes.RACES, String.valueOf(raceId.raceId)));
                } catch (MissingResourceException e) {
                    // TODO temporary catch exception, till all race names won't be parsed (according to code, the game has 117 unique racesIds)
                    LOGGER.info(e.getMessage(), e);
                }
            }
            return creatureRaces;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptySet();
        }
    }

    public CreatureParameterObject getCreatureParameterObjectByCreatureName(String creatureName) {
        ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();
        Dao<CreatureParameterObject, ?> dao;
        try {
            dao = DaoManager.createDao(connectionSource, CreatureParameterObject.class);
            GenericRawResults<CreatureParameterObject> rawResults =
                    dao.queryRaw(
                            "select creature_parameters.* from creature_parameters inner join creature_common_parameters on creature_common_parameters.statsId = creature_parameters.statsId where creature_common_parameters.name = ?",
                            dao.getRawRowMapper(),
                            creatureName);

            return rawResults.getResults().get(0);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    public Integer getRaceIdByCreatureName(String creatureName) {
        ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();
        final Dao<CreatureParameterObject, Integer> parametersObject;
        try {
            parametersObject = DaoManager.createDao(connectionSource, CreatureParameterObject.class);

            GenericRawResults<String> rawResults =
                    parametersObject.queryRaw(
                            "select creature_parameters.raceId from creature_common_parameters inner join creature_parameters on creature_common_parameters.statsId = creature_parameters.statsId where creature_common_parameters.name = ?",
                            new RawRowMapper<String>() {
                                public String mapRow(String[] columnNames,
                                                     String[] resultColumns) {
                                    return resultColumns[0];
                                }
                            },
                            creatureName);

            return Integer.valueOf(rawResults.getResults().get(0));
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return 0;
        }
    }
}
