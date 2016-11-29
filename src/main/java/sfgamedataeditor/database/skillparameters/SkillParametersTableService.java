package sfgamedataeditor.database.skillparameters;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import org.apache.log4j.Logger;
import sfgamedataeditor.database.common.CommonTableService;
import sfgamedataeditor.utils.Pair;

import java.sql.SQLException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public enum SkillParametersTableService {
    INSTANCE;

    private static final Logger LOGGER = Logger.getLogger(SkillParametersTableService.class);

    public void createSkillParametersTable() {
        CommonTableService.INSTANCE.recreateTable(SkillParameterObject.class);
    }

    public void addRecordsToSkillParametersTable(List<Pair<byte[], Long>> offsettedData) {
        CommonTableService.INSTANCE.addRecordsToTable(SkillParameterObject.class, offsettedData);
    }

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
            Set<Integer> skillLevels = new HashSet<>();
            for (SkillParameterObject skillParameterse : skillParameterses) {
                skillLevels.add(skillParameterse.level);
            }

            return skillLevels;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptySet();
        }
    }

    public List<SkillParameterObject> getAllSkillParameters() {
        return CommonTableService.INSTANCE.getAllTableData(SkillParameterObject.class);
    }
}