package sfgamedataeditor.database.objects.chests;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import org.apache.log4j.Logger;
import sfgamedataeditor.database.common.CommonTableService;
import sfgamedataeditor.database.common.OffsetableObject;
import sfgamedataeditor.database.common.TableCreationService;
import sfgamedataeditor.views.utility.Pair;
import sfgamedataeditor.views.utility.ViewTools;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public enum ChestCorpseLootTableService implements TableCreationService {
    INSTANCE {
        @Override
        public void createTable() {
            CommonTableService.INSTANCE.recreateTable(ChestCorpseLootObject.class);
        }

        @Override
        public void addRecordsToTable(List<Pair<byte[], Long>> offsettedData) {
            CommonTableService.INSTANCE.addRecordsToTable(ChestCorpseLootObject.class, offsettedData);
        }

        @Override
        public int getDataLength() {
            return 11;
        }

        @Override
        public Pair<Integer, Integer> getOffsetInterval() {
            return new Pair<>(0x03FA5C53, 0x03FAB489);
        }

        @Override
        public Class<? extends OffsetableObject> getDTOClass() {
            return ChestCorpseLootObject.class;
        }
    };

    private static final Logger LOGGER = Logger.getLogger(ChestCorpseLootTableService.class);

    public Set<String> getAllChestCorpseLootObjectNames() {
        List<ChestCorpseLootObject> allTableData = CommonTableService.INSTANCE.getAllTableData(ChestCorpseLootObject.class);
        Set<String> names = new TreeSet<>();
        for (ChestCorpseLootObject chestCorpseLootObject : allTableData) {
            String objectName = I18NService.INSTANCE.getMessage(I18NTypes.OBJECTS, String.valueOf(chestCorpseLootObject.chestCorpseId));
            names.add(objectName);
        }

        return names;
    }

    public List<ChestCorpseLootObject> getChestLootObjectsByName(String selectedModuleName) {
        Integer chestLootId = ViewTools.getKeyByPropertyValue(selectedModuleName, I18NTypes.OBJECTS);
        ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();
        final Dao<ChestCorpseLootObject, String> dao;
        try {
            dao = DaoManager.createDao(connectionSource, ChestCorpseLootObject.class);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }

        try {
            List<ChestCorpseLootObject> objects = dao.queryBuilder().where().eq("chestCorpseId", chestLootId).query();
            if (objects.isEmpty()) {
                return Collections.emptyList();
            } else {
                return objects;
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }
}
