package sfgamedataeditor.database.items.price.parameters;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import org.apache.log4j.Logger;
import sfgamedataeditor.database.common.CommonTableService;
import sfgamedataeditor.database.common.TableCreationService;
import sfgamedataeditor.dataextraction.DTOOffsetTypes;
import sfgamedataeditor.views.utility.Pair;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum ItemPriceParametersTableService implements TableCreationService {
    INSTANCE {
        @Override
        public void createTable() {
            CommonTableService.INSTANCE.recreateTable(ItemPriceParametersObject.class);
        }

        @Override
        public void addRecordsToTable(List<Pair<byte[], Long>> offsettedData) {
            CommonTableService.INSTANCE.addRecordsToTable(ItemPriceParametersObject.class, offsettedData);
        }

        @Override
        public DTOOffsetTypes getDTOOffsetType() {
            return DTOOffsetTypes.ITEM_PRICES;
        }
    };

    private static final Logger LOGGER = Logger.getLogger(ItemPriceParametersTableService.class);

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
                itemNames.add(I18NService.INSTANCE.getMessage(I18NTypes.ITEMS, String.valueOf(object.itemId)));
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
            // one of possible cases - corpse loot object trying to get so called "Epmty" slot - item with "itemId = 0"
            if (objects.isEmpty()) {
                return null;
            }

            return objects.get(0);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }
}
