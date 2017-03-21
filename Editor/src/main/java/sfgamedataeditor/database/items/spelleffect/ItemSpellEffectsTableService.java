package sfgamedataeditor.database.items.spelleffect;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import org.apache.log4j.Logger;
import sfgamedataeditor.database.common.CommonTableService;
import sfgamedataeditor.database.common.OffsetableObject;
import sfgamedataeditor.database.common.TableCreationService;
import sfgamedataeditor.views.utility.Pair;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public enum ItemSpellEffectsTableService implements TableCreationService {
    INSTANCE {
        @Override
        public void createTable() {
            CommonTableService.INSTANCE.recreateTable(ItemSpellEffectsObject.class);
        }

        @Override
        public void addRecordsToTable(List<Pair<byte[], Long>> offsettedData) {
            CommonTableService.INSTANCE.addRecordsToTable(ItemSpellEffectsObject.class, offsettedData);
        }

        @Override
        public int getDataLength() {
            return 0x5;
        }

        @Override
        public Pair<Integer, Integer> getOffsetInterval() {
            return new Pair<>(0x0009A492, 0x0009F347);
        }

        @Override
        public Class<? extends OffsetableObject> getDTOClass() {
            return ItemSpellEffectsObject.class;
        }
    };

    private static final Logger LOGGER = Logger.getLogger(ItemSpellEffectsTableService.class);

    public List<ItemSpellEffectsObject> getObjectsByItemId(int itemId) {
        ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();
        final Dao<ItemSpellEffectsObject, String> dao;
        try {
            dao = DaoManager.createDao(connectionSource, ItemSpellEffectsObject.class);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }

        try {
            return dao.queryBuilder().where().eq("itemId", itemId).query();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }
}
