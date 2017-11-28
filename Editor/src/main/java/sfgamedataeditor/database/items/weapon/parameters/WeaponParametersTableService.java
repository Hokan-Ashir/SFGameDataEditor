package sfgamedataeditor.database.items.weapon.parameters;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import org.apache.log4j.Logger;
import sfgamedataeditor.database.common.*;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersObject;
import sfgamedataeditor.database.items.price.parameters.ItemPriceParametersTableService;
import sfgamedataeditor.database.text.TextTableService;
import sfgamedataeditor.views.common.ObjectTuple;
import sfgamedataeditor.views.utility.Pair;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public enum WeaponParametersTableService implements TableCreationService {
    INSTANCE {
        private Serializer serializer = new DefaultSerializer();

        @Override
        public void createTable() {
            CommonTableService.INSTANCE.recreateTable(WeaponParametersObject.class);
        }

        @Override
        public void addRecordsToTable(List<Pair<byte[], Long>> offsettedData) {
            CommonTableService.INSTANCE.addRecordsToTable(WeaponParametersObject.class, offsettedData, serializer);
        }

        @Override
        public byte[] serializeObject(OffsetableObject object) {
            return ObjectDataMappingService.INSTANCE.serializeObject(object, serializer);
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

    public List<ObjectTuple> getItemsByItemType(int typeId) {
        ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();
        final Dao<WeaponParametersObject, String> dao;
        try {
            dao = DaoManager.createDao(connectionSource, WeaponParametersObject.class);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }

        try {
            List<WeaponParametersObject> objects = dao.queryBuilder().selectColumns("itemId").where().eq("type", typeId).query();
            Integer[] itemIds = new Integer[objects.size()];
            for (int i = 0; i < objects.size(); i++) {
                itemIds[i] = objects.get(i).itemId;
            }

            List<ItemPriceParametersObject> priceParametersObjects = ItemPriceParametersTableService.INSTANCE.getObjectByItemIds(itemIds);
            if (priceParametersObjects == null || priceParametersObjects.isEmpty()) {
                return Collections.emptyList();
            }
            Integer[] nameIds = new Integer[priceParametersObjects.size()];
            Integer[] objectIds = new Integer[priceParametersObjects.size()];
            for (int i = 0; i < priceParametersObjects.size(); i++) {
                nameIds[i] = priceParametersObjects.get(i).nameId;
                objectIds[i] = priceParametersObjects.get(i).itemId;
            }

            return TextTableService.INSTANCE.getObjectTuples(nameIds, objectIds);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }
}
