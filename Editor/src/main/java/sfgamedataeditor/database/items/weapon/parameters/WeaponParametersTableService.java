package sfgamedataeditor.database.items.weapon.parameters;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import org.apache.log4j.Logger;
import sfgamedataeditor.database.common.CommonTableService;
import sfgamedataeditor.database.common.OffsetableObject;
import sfgamedataeditor.database.common.TableCreationService;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.views.utility.Pair;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public enum WeaponParametersTableService implements TableCreationService {
    INSTANCE {
        @Override
        public void createTable() {
            CommonTableService.INSTANCE.recreateTable(WeaponParametersObject.class);
        }

        @Override
        public void addRecordsToTable(List<Pair<byte[], Long>> offsettedData) {
            CommonTableService.INSTANCE.addRecordsToTable(WeaponParametersObject.class, offsettedData);
        }

        @Override
        public int getDataLength() {
            return 16;
        }

        @Override
        public Pair<Integer, Integer> getOffsetInterval() {
            return new Pair<>(0x00091734, 0x00094443);
        }

        @Override
        public Class<? extends OffsetableObject> getDTOClass() {
            return WeaponParametersObject.class;
        }
    };

    private static final Logger LOGGER = Logger.getLogger(WeaponParametersTableService.class);

    public WeaponParametersObject getObjectByItemId(int itemId) {
        ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();
        final Dao<WeaponParametersObject, String> dao;
        try {
            dao = DaoManager.createDao(connectionSource, WeaponParametersObject.class);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }

        try {
            List<WeaponParametersObject> objects = dao.queryBuilder().where().eq("itemId", itemId).query();
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

    public Set<String> getItemsByItemType(int typeId) {
        ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();
        final Dao<WeaponParametersObject, String> dao;
        try {
            dao = DaoManager.createDao(connectionSource, WeaponParametersObject.class);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptySet();
        }

        try {
            List<WeaponParametersObject> objects = dao.queryBuilder().where().eq("type", typeId).query();
            Set<String> itemNames = new TreeSet<>();
            for (WeaponParametersObject object : objects) {
                itemNames.add(I18NService.INSTANCE.getMessage(I18NTypes.ITEMS, String.valueOf(object.itemId)));
            }

            return itemNames;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptySet();
        }
    }

    public Integer getItemIdByItemTypeAndName(String itemName, int typeId) {
        Set<String> itemNames = getItemsByItemType(typeId);
        for (String name : itemNames) {
            if (name.equals(itemName)) {
                return ItemPriceParametersTableService.INSTANCE.getItemIdByItemNameAndType(itemName);
            }
        }

        return null;
    }
}
