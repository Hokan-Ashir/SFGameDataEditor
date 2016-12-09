package sfgamedataeditor.database.items.price.parameters;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import org.apache.log4j.Logger;
import sfgamedataeditor.database.common.CommonTableService;
import sfgamedataeditor.views.utility.Pair;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.MissingResourceException;

public enum ItemPriceParametersTableService {
    INSTANCE;

    private static final Logger LOGGER = Logger.getLogger(ItemPriceParametersTableService.class);

    public void createItemPriceParametersTable() {
        CommonTableService.INSTANCE.recreateTable(ItemPriceParametersObject.class);
    }

    public void addRecordsToItemPriceParametersTable(List<Pair<byte[], Long>> offsettedData) {
        CommonTableService.INSTANCE.addRecordsToTable(ItemPriceParametersObject.class, offsettedData);
    }

    public List<String> getItemsByItemType(int typeId) {
        ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();
        final Dao<ItemPriceParametersObject, String> dao;
        try {
            dao = DaoManager.createDao(connectionSource, ItemPriceParametersObject.class);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }

        try {
            List<ItemPriceParametersObject> objects = dao.queryBuilder().where().eq("typeId", typeId).query();
            List<String> itemNames = new ArrayList<>();
            for (ItemPriceParametersObject object : objects) {
                try {
                    itemNames.add(I18NService.INSTANCE.getMessage(I18NTypes.ITEMS, String.valueOf(object.itemId)));
                } catch (MissingResourceException e) {
                    System.out.println();
                    // TODO remove later, when all items from all companies have been included in i18n resource files
                    // currently it began to fail searching items with ids ~ 7000
                }
            }

            return itemNames;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    public int getItemTypeIdByItemId(int itemId) {
        ItemPriceParametersObject object = getObjectByItemId(itemId);
        if (object == null) {
            return 0;
        } else {
            return object.typeId;
        }
    }

    public ItemPriceParametersObject getObjectByItemId(int itemId) {
        ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();
        final Dao<ItemPriceParametersObject, String> dao;
        try {
            dao = DaoManager.createDao(connectionSource, ItemPriceParametersObject.class);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }

        try {
            List<ItemPriceParametersObject> objects = dao.queryBuilder().where().eq("itemId", itemId).query();
            return objects.get(0);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }
}
