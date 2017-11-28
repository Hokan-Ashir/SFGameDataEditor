package sfgamedataeditor.database.skill.parameters;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import org.apache.log4j.Logger;
import sfgamedataeditor.database.common.*;
import sfgamedataeditor.views.utility.Pair;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public enum SkillParametersTableService implements TableCreationService {
    INSTANCE {

        private Serializer serializer = new DefaultSerializer();

        @Override
        public void createTable() {
            CommonTableService.INSTANCE.recreateTable(SkillParameterObject.class);
        }

        @Override
        public void addRecordsToTable(List<Pair<byte[], Long>> offsettedData) {
            CommonTableService.INSTANCE.addRecordsToTable(SkillParameterObject.class, offsettedData, serializer);
        }

        @Override
        public byte[] serializeObject(OffsetableObject object) {
            return ObjectDataMappingService.INSTANCE.serializeObject(object, serializer);
        }

        @Override
        public int getDataLength() {
            return 0x9;
        }

        @Override
        public Pair<Integer, Integer> getOffsetInterval() {
            return new Pair<>(0x03F85FD4, 0x03F864BF);
        }

        @Override
        public Class<? extends OffsetableObject> getDTOClass() {
            return SkillParameterObject.class;
        }
    };

    private static final Logger LOGGER = Logger.getLogger(SkillParametersTableService.class);

    public SkillParameterObject getSkillParameter(int skillSchoolId, int skillLevel) {
        ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();
        Dao<SkillParameterObject, ?> dao;
        try {
            dao = DaoManager.createDao(connectionSource, SkillParameterObject.class);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }

        try {
            List<SkillParameterObject> query = dao.queryBuilder().where().eq("skillTypeId", skillSchoolId).and().eq("level", skillLevel).query();
            if (query.isEmpty()) {
                return null;
            }
            return query.get(0);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    public Set<Integer> getSkillPossibleLevels(int skillSchoolId) {
        ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();
        Dao<SkillParameterObject, ?> dao;
        try {
            dao = DaoManager.createDao(connectionSource, SkillParameterObject.class);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptySet();
        }

        try {
            List<SkillParameterObject> skillParameterses = dao.queryBuilder().selectColumns("level").where().eq("skillTypeId", skillSchoolId).query();
            Set<Integer> skillLevels = new TreeSet<>();
            for (SkillParameterObject skillParameterse : skillParameterses) {
                skillLevels.add(skillParameterse.level);
            }

            return skillLevels;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptySet();
        }
    }
}
