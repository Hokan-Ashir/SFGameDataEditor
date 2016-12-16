package sfgamedataeditor.database.items.spelleffect;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import org.apache.log4j.Logger;
import sfgamedataeditor.database.common.CommonTableService;
import sfgamedataeditor.database.common.TableCreationService;
import sfgamedataeditor.dataextraction.DTOOffsetTypes;
import sfgamedataeditor.views.utility.Pair;

import java.sql.SQLException;
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
        public DTOOffsetTypes getDTOOffsetType() {
            return DTOOffsetTypes.ITEM_SPELL_EFFECTS;
        }
    };

    private static final Logger LOGGER = Logger.getLogger(ItemSpellEffectsTableService.class);

    public ItemSpellEffectsObject getObjectByItemId(int itemId) {
        ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();
        final Dao<ItemSpellEffectsObject, String> dao;
        try {
            dao = DaoManager.createDao(connectionSource, ItemSpellEffectsObject.class);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }

        try {
            List<ItemSpellEffectsObject> objects = dao.queryBuilder().where().eq("itemId", itemId).query();
            // item has NO effects, i.e. most of items except weapons
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
}
