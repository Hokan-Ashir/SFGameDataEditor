package sfgamedataeditor.database.creatures;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import org.apache.log4j.Logger;
import sfgamedataeditor.database.common.CommonTableService;
import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.utils.Pair;

import java.sql.SQLException;
import java.util.*;

public enum CreatureParametersTableService {
    INSTANCE;

    private static final Logger LOGGER = Logger.getLogger(CreatureParametersTableService.class);
    private static final String RACES_NAME_MAPPING_FILENAME = "racesNameMapping";
    private static final ResourceBundle BUNDLE = ResourceBundle.getBundle(RACES_NAME_MAPPING_FILENAME, Locale.getDefault());
    private static final String RACE_I18N_PREFIX = "race";

    public void createCreatureParametersTable() {
        CommonTableService.INSTANCE.recreateTable(CreatureParameterObject.class);
    }

    public void addRecordsToSkillParametersTable(List<Pair<byte[], Long>> offsettedData) {
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
                    String raceName = BUNDLE.getString(String.valueOf(raceId.raceId));
                    creatureRaces.add(I18N.INSTANCE.getMessage(RACE_I18N_PREFIX + "." + raceName));
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

    public List<CreatureParameterObject> getCreaturesByRaceIdName(String i18nRaceName) {
        int raceId = 0;
        Set<String> strings = BUNDLE.keySet();
        for (String key : strings) {
            if (I18N.INSTANCE.getMessage(RACE_I18N_PREFIX + "." + BUNDLE.getString(key)).equals(i18nRaceName)) {
                raceId = Integer.parseInt(key);
            }
        }
        ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();
        Dao<CreatureParameterObject, ?> dao;
        try {
            dao = DaoManager.createDao(connectionSource, CreatureParameterObject.class);
            return dao.queryBuilder().where().eq("raceId", raceId).query();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    public List<CreatureParameterObject> getAllCreatureParameters() {
        return CommonTableService.INSTANCE.getAllTableData(CreatureParameterObject.class);
    }
}
