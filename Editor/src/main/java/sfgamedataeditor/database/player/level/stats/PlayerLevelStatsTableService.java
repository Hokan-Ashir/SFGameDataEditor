package sfgamedataeditor.database.player.level.stats;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import org.apache.log4j.Logger;
import sfgamedataeditor.database.common.CommonTableService;
import sfgamedataeditor.database.common.OffsetableObject;
import sfgamedataeditor.database.common.TableCreationService;
import sfgamedataeditor.views.utility.Pair;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public enum PlayerLevelStatsTableService implements TableCreationService {
    INSTANCE {
        @Override
        public void createTable() {
            CommonTableService.INSTANCE.recreateTable(PlayerLevelStatsObject.class);
        }

        @Override
        public void addRecordsToTable(List<Pair<byte[], Long>> offsettedData) {
            CommonTableService.INSTANCE.addRecordsToTable(PlayerLevelStatsObject.class, offsettedData);
        }

        @Override
        public int getDataLength() {
            return 15;
        }

        @Override
        public Pair<Integer, Integer> getOffsetInterval() {
            return new Pair<>(0x03F8F1D7, 0x03F8F4C5);
        }

        @Override
        public Class<? extends OffsetableObject> getDTOClass() {
            return PlayerLevelStatsObject.class;
        }
    };

    private static final Logger LOGGER = Logger.getLogger(PlayerLevelStatsTableService.class);

    public PlayerLevelStatsObject getObjectByLevel(int level) {
        ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();
        final Dao<PlayerLevelStatsObject, String> dao;
        try {
            dao = DaoManager.createDao(connectionSource, PlayerLevelStatsObject.class);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }

        try {
            List<PlayerLevelStatsObject> objects = dao.queryBuilder().where().eq("level", level).query();
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

    public Set<Integer> getPlayerStatsLevels() {
        Set<Integer> result = new TreeSet<>();
        List<PlayerLevelStatsObject> tableData = CommonTableService.INSTANCE.getAllTableData(PlayerLevelStatsObject.class);
        for (PlayerLevelStatsObject object : tableData) {
            result.add(object.level);
        }

        return result;
    }
}
