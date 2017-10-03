package sfgamedataeditor.database.items.price.parameters;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.Where;
import com.j256.ormlite.support.ConnectionSource;
import org.apache.log4j.Logger;
import sfgamedataeditor.database.common.CommonTableService;
import sfgamedataeditor.database.common.OffsetableObject;
import sfgamedataeditor.database.common.TableCreationService;
import sfgamedataeditor.database.text.TextObject;
import sfgamedataeditor.database.text.TextTableService;
import sfgamedataeditor.views.common.ObjectTuple;
import sfgamedataeditor.views.utility.Pair;

import java.sql.SQLException;
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
        public int getDataLength() {
            return 22;
        }

        @Override
        public Pair<Integer, Integer> getOffsetInterval() {
            return new Pair<>(0x0006359E, 0x000897DB);
        }

        @Override
        public Class<? extends OffsetableObject> getDTOClass() {
            return ItemPriceParametersObject.class;
        }
    };

    private static final Logger LOGGER = Logger.getLogger(ItemPriceParametersTableService.class);

    public List<ObjectTuple> getItemsByItemType(int typeId) {
        ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();
        final Dao<ItemPriceParametersObject, String> dao;
        try {
            dao = DaoManager.createDao(connectionSource, ItemPriceParametersObject.class);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }


        try {
            List<ItemPriceParametersObject> objects = dao.queryBuilder().selectColumns("itemId", "nameId").orderBy("nameId", true).where().eq("typeId", typeId).query();
            Integer[] textIds = new Integer[objects.size()];
            Integer[] objectIds = new Integer[objects.size()];
            for (int i = 0; i < objects.size(); ++i) {
                textIds[i] = objects.get(i).nameId;
                objectIds[i] = objects.get(i).itemId;
            }

            return TextTableService.INSTANCE.getObjectTuples(textIds, objectIds);
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
        List<ItemPriceParametersObject> objects = getObjectByItemIds(new Integer[]{itemId});
        return objects.get(0);
    }

    public List<ItemPriceParametersObject> getObjectByItemIds(Integer[] itemIds) {
        ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();
        final Dao<ItemPriceParametersObject, String> dao;
        try {
            dao = DaoManager.createDao(connectionSource, ItemPriceParametersObject.class);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }

        try {
            List<ItemPriceParametersObject> objects = dao.queryBuilder().where().in("itemId", (Object[]) itemIds).query();
            // one of possible cases - corpse loot object trying to get so called "Epmty" slot - item with "itemId = 0"
            if (objects.isEmpty()) {
                return null;
            }

            return objects;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    public List<ObjectTuple> getItemNameIdPairByTextIds(List<Integer> textIds, Long limit, Integer typeId) {
        ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();
        final Dao<ItemPriceParametersObject, String> dao;
        try {
            dao = DaoManager.createDao(connectionSource, ItemPriceParametersObject.class);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }

        try {
            List<ItemPriceParametersObject> objects = dao.queryBuilder().selectColumns("nameId", "itemId").limit(limit).where().eq("typeId", typeId).and().in("nameId", textIds).query();
            Integer[] nameIds = new Integer[objects.size()];
            Integer[] objectIds = new Integer[objects.size()];
            for (int i = 0; i < objects.size(); ++i) {
                nameIds[i] = objects.get(i).nameId;
                objectIds[i] = objects.get(i).itemId;
            }

            return TextTableService.INSTANCE.getObjectTuples(nameIds, objectIds);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    public Integer getItemIdByItemName(String name) {
        return getItemIdByItemNameAndType(name);
    }

    public Integer getItemIdByItemNameAndType(String name, Integer... typeId) {
        if (name.startsWith(TextTableService.NULL_OBJECT_PREFIX)) {
            return Integer.valueOf(name.split(" - ")[1]);
        }
        ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();
        final Dao<ItemPriceParametersObject, String> dao;
        try {
            dao = DaoManager.createDao(connectionSource, ItemPriceParametersObject.class);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }

        try {

            List<TextObject> textObjects = TextTableService.INSTANCE.getObjectsByName(name);
            Integer[] nameIds = new Integer[textObjects.size()];
            for (int i = 0; i < textObjects.size(); ++i) {
                nameIds[i] = textObjects.get(i).textId;
            }

            Where<ItemPriceParametersObject, String> where = dao.queryBuilder().selectColumns("itemId").where().in("nameId", (Object[]) nameIds);
            if (typeId != null && typeId.length != 0) {
                where = where.and().in("typeId", (Object[]) typeId);
            }

            // TODO for some reason ItemPriceParametersObject for type = 2 (items.rune.hero.in.inventory) has incorrect values
            // some runes that should have type = 3 (items.rune.hero.in.added) also have type = 2; this can be fixed via adding Prepatcher
            // during data uploading phase, need to test, does it affect users and will this Prepatcher affect users as well
            // we CAN'T add additional select filter, i.e. on copperSellingPrice/copperByingPrice cause this will limit users functionality
            List<ItemPriceParametersObject> objects = where.query();
            if (objects.isEmpty()) {
                return null;
            } else {
                return objects.get(0).itemId;
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    public Integer getItemIdByNameAndLevel(String baseItemName, Integer itemLevel, Integer itemType) {
        ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();
        final Dao<ItemPriceParametersObject, String> dao;
        try {
            dao = DaoManager.createDao(connectionSource, ItemPriceParametersObject.class);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }

        try {
            String regExp = "^[^\\d]*" + baseItemName + "[^\\d]*";
            if (itemLevel != null) {
                regExp += itemLevel + "[^\\d]*$";
            }

            List<Integer> textIDs = TextTableService.INSTANCE.getObjectsNameIdsByRegExp(regExp);
            List<ItemPriceParametersObject> objects = dao.queryBuilder().selectColumns("itemId").where()
                    .in("nameId", textIDs.toArray())
                    .and().eq("typeId", itemType).query();
            if (objects.isEmpty()) {
                return null;
            } else {
                return objects.get(0).itemId;
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }
}
