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
import sfgamedataeditor.database.common.OffsetableObject;
import sfgamedataeditor.database.common.TableCreationService;
import sfgamedataeditor.database.text.TextTableService;
import sfgamedataeditor.views.common.ObjectTuple;
import sfgamedataeditor.views.utility.Pair;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public enum CreatureCommonParametersTableService implements TableCreationService {
    INSTANCE {
        @Override
        public void createTable() {
            CommonTableService.INSTANCE.recreateTable(CreaturesCommonParameterObject.class);
        }

        @Override
        public void addRecordsToTable(List<Pair<byte[], Long>> offsettedData) {
            CommonTableService.INSTANCE.addRecordsToTable(CreaturesCommonParameterObject.class, offsettedData);
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

    public List<ObjectTuple> getCreatureNamesByRaceId(int raceId) {
        ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();
        final Dao<CreaturesCommonParameterObject, Integer> commonParametersDAO;
        try {
            commonParametersDAO = DaoManager.createDao(connectionSource, CreaturesCommonParameterObject.class);

            GenericRawResults<Pair<Integer, Integer>> rawResults =
                    commonParametersDAO.queryRaw(
                            "select creature_common_parameters.nameId, creature_common_parameters.creatureId " +
                                    "from creature_common_parameters " +
                                    "inner join creature_parameters on " +
                                    "creature_common_parameters.statsId = creature_parameters.statsId " +
                                    "where creature_parameters.raceId = ? " +
                                    "order by creature_common_parameters.nameId asc",
                            new RawRowMapper<Pair<Integer, Integer>>() {
                                public Pair<Integer, Integer> mapRow(String[] columnNames,
                                                     String[] resultColumns) {
                                    return new Pair<>(Integer.valueOf(resultColumns[0]), Integer.valueOf(resultColumns[1]));
                                }
                            },
                            String.valueOf(raceId));

            List<Pair<Integer, Integer>> results = rawResults.getResults();
            Integer[] nameIds = new Integer[results.size()];
            Integer[] objectIds = new Integer[results.size()];
            for (int i = 0; i < results.size(); ++i) {
                nameIds[i] = results.get(i).getKey();
                objectIds[i] = results.get(i).getValue();
            }

            return TextTableService.INSTANCE.getObjectTuples(nameIds, objectIds);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
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

    public List<ObjectTuple> getCreaturesNameIdPairByItemNamePart(String namePart, Long limit) {
        List<CreaturesCommonParameterObject> objects = getObjectsByNamePart(namePart, limit);
        Integer[] nameIds = new Integer[objects.size()];
        Integer[] objectIds = new Integer[objects.size()];
        for (int i = 0; i < objects.size(); ++i) {
            nameIds[i] = objects.get(i).nameId;
            objectIds[i] = objects.get(i).creatureId;
        }

        return TextTableService.INSTANCE.getObjectTuples(nameIds, objectIds);
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
            return builder.selectColumns("creatureId", "nameId").where().like("name", selectArg).query();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    public List<ObjectTuple> getCreatureTuples(Integer[] creatureIds) {
        ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();
        final Dao<CreaturesCommonParameterObject, String> dao;
        try {
            dao = DaoManager.createDao(connectionSource, CreaturesCommonParameterObject.class);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }

        try {
            List<CreaturesCommonParameterObject> objects = dao.queryBuilder().selectColumns("creatureId", "nameId").orderBy("nameId", true).where().in("creatureId", (Object[]) creatureIds).query();
            if (objects.isEmpty()) {
                return null;
            } else {

                Integer[] nameIds = new Integer[objects.size()];
                Integer[] objectIds = new Integer[objects.size()];
                for (int i = 0; i < objects.size(); i++) {
                    nameIds[i] = objects.get(i).nameId;
                    objectIds[i] = objects.get(i).creatureId;
                }

                return TextTableService.INSTANCE.getObjectTuples(nameIds, objectIds);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }
}
