package sfgamedataeditor.database.creatures.common;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.dao.RawRowMapper;
import com.j256.ormlite.support.ConnectionSource;
import org.apache.log4j.Logger;
import sfgamedataeditor.database.common.CommonTableService;
import sfgamedataeditor.database.common.DTODecorator;
import sfgamedataeditor.views.utility.Pair;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

public enum CreatureCommonParametersTableService {
    INSTANCE;

    private static final Logger LOGGER = Logger.getLogger(CreatureCommonParametersTableService.class);

    public void createCommonCreatureParametersTable() {
        CommonTableService.INSTANCE.recreateTable(CreaturesCommonParameterObject.class);
    }

    public void addRecordsToCommonCreatureParametersTable(List<Pair<byte[], Long>> offsettedData, DTODecorator<CreaturesCommonParameterObject> decorator) {
        CommonTableService.INSTANCE.addRecordsToTable(CreaturesCommonParameterObject.class, offsettedData, decorator);
    }

    public List<String> getCreatureNamesByRaceName(String i18nRaceName) {
        int raceId = getRaceId(i18nRaceName);
        return getCreatureNamesByRaceId(raceId);
    }

    public List<String> getCreatureNamesByRaceId(int raceId) {
        ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();
        final Dao<CreaturesCommonParameterObject, Integer> commonParametersDAO;
        try {
            commonParametersDAO = DaoManager.createDao(connectionSource, CreaturesCommonParameterObject.class);

            GenericRawResults<String> rawResults =
                    commonParametersDAO.queryRaw(
                            "select name from creature_common_parameters inner join creature_parameters on creature_common_parameters.statsId = creature_parameters.statsId where creature_parameters.raceId = ?",
                            new RawRowMapper<String>() {
                                public String mapRow(String[] columnNames,
                                                     String[] resultColumns) {
                                    return resultColumns[0];
                                }
                            },
                            String.valueOf(raceId));

            return rawResults.getResults();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    private int getRaceId(String i18nRaceName) {
        int raceId = 0;
        ResourceBundle bundle = I18NService.INSTANCE.getBundle(I18NTypes.RACES);
        Set<String> strings = bundle.keySet();
        for (String key : strings) {
            if (bundle.getString(key).equals(i18nRaceName)) {
                raceId = Integer.parseInt(key);
            }
        }

        return raceId;
    }

    public String getCreatureNameById(int statsId) {
        ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();
        Dao<CreaturesCommonParameterObject, ?> dao;
        try {
            dao = DaoManager.createDao(connectionSource, CreaturesCommonParameterObject.class);
            GenericRawResults<String> rawResults =
                    dao.queryRaw(
                            "select name from creature_common_parameters inner join creature_parameters on creature_common_parameters.statsId = creature_parameters.statsId where creature_common_parameters.statsId = ?",
                            new RawRowMapper<String>() {
                                @Override
                                public String mapRow(String[] columnsNames, String[] resultValues) throws SQLException {
                                    return resultValues[0];
                                }
                            },
                            String.valueOf(statsId));

            return rawResults.getResults().get(0);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }
}
