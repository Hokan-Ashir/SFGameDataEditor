package sfgamedataeditor.database.creatures.common;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.dao.RawRowMapper;
import com.j256.ormlite.support.ConnectionSource;
import org.apache.log4j.Logger;
import sfgamedataeditor.database.common.CommonTableService;
import sfgamedataeditor.database.common.DTODecorator;
import sfgamedataeditor.database.common.TableCreationService;
import sfgamedataeditor.dataextraction.DTOOffsetTypes;
import sfgamedataeditor.views.utility.Pair;
import sfgamedataeditor.views.utility.ViewTools;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.sql.SQLException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public enum CreatureCommonParametersTableService implements TableCreationService {
    INSTANCE {
        @Override
        public void createTable() {
            CommonTableService.INSTANCE.recreateTable(CreaturesCommonParameterObject.class);
        }

        @Override
        public void addRecordsToTable(List<Pair<byte[], Long>> offsettedData) {
            CommonTableService.INSTANCE.addRecordsToTable(CreaturesCommonParameterObject.class, offsettedData, new CreaturesObjectDecorator());
        }

        @Override
        public DTOOffsetTypes getDTOOffsetType() {
            return DTOOffsetTypes.CREATURE_COMMON_PARAMETERS;
        }
    };

    private static final Logger LOGGER = Logger.getLogger(CreatureCommonParametersTableService.class);

    public Set<String> getCreatureNamesByRaceName(String i18nRaceName) {
        int raceId = ViewTools.getKeyByPropertyValue(i18nRaceName, I18NTypes.RACES);
        return getCreatureNamesByRaceId(raceId);
    }

    public Set<String> getCreatureNamesByRaceId(int raceId) {
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

            return new HashSet<>(rawResults.getResults());
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptySet();
        }
    }

    public CreaturesCommonParameterObject getCreatureParametersByCreatureId(int creatureId) {
        ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();
        final Dao<CreaturesCommonParameterObject, String> dao;
        try {
            dao = DaoManager.createDao(connectionSource, CreaturesCommonParameterObject.class);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }

        try {
            List<CreaturesCommonParameterObject> objects = dao.queryBuilder().where().eq("creatureId", creatureId).query();
            if (objects.isEmpty()) {
                return null;
            } else {
                return objects.get(0);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    private static final class CreaturesObjectDecorator implements DTODecorator<CreaturesCommonParameterObject> {

        @Override
        public CreaturesCommonParameterObject decorateObject(CreaturesCommonParameterObject object) {
            Integer creatureId = object.creatureId;
            object.name = I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, String.valueOf(creatureId));
            return object;
        }
    }
}
