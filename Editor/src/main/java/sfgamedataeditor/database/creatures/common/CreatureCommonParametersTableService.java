package sfgamedataeditor.database.creatures.common;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.dao.RawRowMapper;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.SelectArg;
import com.j256.ormlite.support.ConnectionSource;
import org.apache.log4j.Logger;
import sfgamedataeditor.database.common.CommonTableService;
import sfgamedataeditor.database.common.DTODecorator;
import sfgamedataeditor.database.common.OffsetableObject;
import sfgamedataeditor.database.common.TableCreationService;
import sfgamedataeditor.views.utility.Pair;
import sfgamedataeditor.views.utility.ViewTools;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.sql.SQLException;
import java.util.*;

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
        public int getDataLength() {
            return 64;
        }

        @Override
        public Pair<Integer, Integer> getOffsetInterval() {
            return new Pair<>(0x03F4CDE6, 0x03F75C25);
        }

        @Override
        public Class<? extends OffsetableObject> getDTOClass() {
            return CreaturesCommonParameterObject.class;
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

            return new TreeSet<>(rawResults.getResults());
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

    public List<Pair<String, Integer>> getCreaturesNameIdPairByItemNamePart(String namePart, Long limit) {
        List<CreaturesCommonParameterObject> objects = getObjectsByNamePart(namePart, limit);
        List<Pair<String, Integer>> result = new ArrayList<>();
        for (CreaturesCommonParameterObject object : objects) {
            result.add(new Pair<>(object.name, object.creatureId));
        }

        return result;
    }

    public Integer getCreatureIdByName(String name) {
        ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();
        final Dao<CreaturesCommonParameterObject, String> dao;
        try {
            dao = DaoManager.createDao(connectionSource, CreaturesCommonParameterObject.class);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }

        try {
            SelectArg selectArg = new SelectArg(name);
            QueryBuilder<CreaturesCommonParameterObject, String> builder = dao.queryBuilder();
            List<CreaturesCommonParameterObject> objects = builder.where().like("name", selectArg).query();
            if (objects.isEmpty()) {
                return null;
            } else {
                return objects.get(0).creatureId;
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    private List<CreaturesCommonParameterObject> getObjectsByNamePart(String partName, Long limit) {
        ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();
        final Dao<CreaturesCommonParameterObject, String> dao;
        try {
            dao = DaoManager.createDao(connectionSource, CreaturesCommonParameterObject.class);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }

        try {
            SelectArg selectArg = new SelectArg("%" + partName + "%");
            QueryBuilder<CreaturesCommonParameterObject, String> builder = dao.queryBuilder().limit(limit);
            return builder.where().like("name", selectArg).query();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
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
