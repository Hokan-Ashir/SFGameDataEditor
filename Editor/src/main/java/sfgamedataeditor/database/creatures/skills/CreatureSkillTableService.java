package sfgamedataeditor.database.creatures.skills;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import org.apache.log4j.Logger;
import sfgamedataeditor.database.common.CommonTableService;
import sfgamedataeditor.database.common.TableCreationService;
import sfgamedataeditor.views.utility.Pair;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public enum CreatureSkillTableService implements TableCreationService {
    INSTANCE {
        @Override
        public void createTable() {
            CommonTableService.INSTANCE.recreateTable(CreatureSkillObject.class);
        }

        @Override
        public void addRecordsToTable(List<Pair<byte[], Long>> offsettedData) {
            CommonTableService.INSTANCE.addRecordsToTable(CreatureSkillObject.class, offsettedData);
        }

        @Override
        public int getDataLength() {
            return 0x5;
        }

        @Override
        public Pair<Integer, Integer> getOffsetInterval() {
            return new Pair<>(0x000613C1, 0x00062E96);
        }
    };

    private static final Logger LOGGER = Logger.getLogger(CreatureSkillTableService.class);

    public List<CreatureSkillObject> getCreatureSkillsByStatsId(int creatureStatsId) {
        ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();
        Dao<CreatureSkillObject, ?> dao;
        try {
            dao = DaoManager.createDao(connectionSource, CreatureSkillObject.class);
            return dao.queryBuilder().where().eq("creatureStatsId", creatureStatsId).query();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }
}
