package sfgamedataeditor.database.tableservices;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import org.apache.log4j.Logger;
import sfgamedataeditor.database.objects.SkillParameters;
import sfgamedataeditor.databind.Pair;

import java.sql.SQLException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public enum SkillParametersTableService {
    INSTANCE;

    private static final Logger LOGGER = Logger.getLogger(SkillParametersTableService.class);

    public void createSkillParametersTable() {
        CommonTableService.INSTANCE.recreateTable(SkillParameters.class);
    }

    public void addRecordsToSkillParametersTable(List<Pair<byte[], Long>> offsettedData) {
        CommonTableService.INSTANCE.addRecordsToTable(SkillParameters.class, offsettedData);
    }

    public SkillParameters getSkillParameter(int skillSchoolId, int skillLevel) {
        ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();
        Dao<SkillParameters, ?> dao;
        try {
            dao = DaoManager.createDao(connectionSource, SkillParameters.class);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }

        try {
            List<SkillParameters> query = dao.queryBuilder().where().eq("skillTypeId", skillSchoolId).and().eq("level", skillLevel).query();
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
        Dao<SkillParameters, ?> dao;
        try {
            dao = DaoManager.createDao(connectionSource, SkillParameters.class);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptySet();
        }

        try {
            List<SkillParameters> skillParameterses = dao.queryBuilder().selectColumns("level").where().eq("skillTypeId", skillSchoolId).query();
            Set<Integer> skillLevels = new HashSet<>();
            for (SkillParameters skillParameterse : skillParameterses) {
                skillLevels.add(skillParameterse.level);
            }

            return skillLevels;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptySet();
        }
    }

    public List<SkillParameters> getAllSkillParameters() {
        return CommonTableService.INSTANCE.getAllTableData(SkillParameters.class);
    }
}
