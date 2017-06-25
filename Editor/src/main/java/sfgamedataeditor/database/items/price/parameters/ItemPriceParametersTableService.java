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
import sfgamedataeditor.views.common.SubViewPanelTuple;
import sfgamedataeditor.views.utility.Pair;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.sql.SQLException;
import java.util.*;

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

    public List<SubViewPanelTuple> getItemsByItemType(int typeId) {
        ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();
        final Dao<ItemPriceParametersObject, String> dao;
        try {
            dao = DaoManager.createDao(connectionSource, ItemPriceParametersObject.class);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }


        try {
            List<ItemPriceParametersObject> objects = dao.queryBuilder().selectColumns("itemId", "nameId").where().eq("typeId", typeId).query();
            Integer[] textIds = new Integer[objects.size()];
            for (int i = 0; i < objects.size(); ++i) {
                textIds[i] = objects.get(i).nameId;
            }

            // TODO set correct language id
            Dao<TextObject, String>  textObjectDAO = DaoManager.createDao(connectionSource, TextObject.class);
            List<TextObject> textObjects = textObjectDAO.queryBuilder().selectColumns("textId", "text").where().in("textId", (Object[]) textIds).and().eq("languageId", 1).query();
            List<SubViewPanelTuple> result = new ArrayList<>();
            for (int i = 0; i < textObjects.size(); ++i) {
                result.add(new SubViewPanelTuple(textObjects.get(i).text, objects.get(i).itemId));
            }

            return result;
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

    public List<Pair<String, Integer>> getItemNameIdPairByItemNamePart(String namePart, Long limit, Integer typeId) {
        ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();
        final Dao<ItemPriceParametersObject, String> dao;
        try {
            dao = DaoManager.createDao(connectionSource, ItemPriceParametersObject.class);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }

        try {
            List<ItemPriceParametersObject> objects = dao.queryBuilder().selectColumns("nameId", "itemId").limit(limit).where().eq("typeId", typeId).query();
            Integer[] textIds = new Integer[objects.size()];
            for (int i = 0; i < objects.size(); ++i) {
                textIds[i] = objects.get(i).nameId;
            }

            // TODO set correct language id
            Dao<TextObject, String>  textObjectDAO = DaoManager.createDao(connectionSource, TextObject.class);
            List<TextObject> textObjects = textObjectDAO.queryBuilder().selectColumns("text").where().in("textId", (Object[]) textIds).and().eq("languageId", 1).query();

            // TODO what about ordering inside arraylist?
            List<Pair<String, Integer>> result = new ArrayList<>();
            for (int i = 0; i < textObjects.size(); ++i) {
                result.add(new Pair<>(textObjects.get(i).text, objects.get(i).itemId));
            }

            return result;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    public Integer getItemIdByItemName(String name) {
        return getItemIdByItemNameAndType(name);
    }

    public Integer getItemIdByItemNameAndType(String name, Integer... typeId) {
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
}
