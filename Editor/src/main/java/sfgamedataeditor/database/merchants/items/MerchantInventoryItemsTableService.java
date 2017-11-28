package sfgamedataeditor.database.merchants.items;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.dao.RawRowMapper;
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

public enum MerchantInventoryItemsTableService implements TableCreationService {
    INSTANCE {
        private Serializer serializer = new DefaultSerializer();

        @Override
        public void createTable() {
            CommonTableService.INSTANCE.recreateTable(MerchantInventoryItemsObject.class);
        }

        @Override
        public void addRecordsToTable(List<Pair<byte[], Long>> offsettedData) {
            CommonTableService.INSTANCE.addRecordsToTable(MerchantInventoryItemsObject.class, offsettedData, serializer);
        }

        @Override
        public byte[] serializeObject(OffsetableObject object) {
            return ObjectDataMappingService.INSTANCE.serializeObject(object, serializer);
        }

        @Override
        public int getDataLength() {
            return 0x6;
        }

        @Override
        public Pair<Integer, Integer> getOffsetInterval() {
            return new Pair<>(0x03F867D8, 0x03F8E7AB);
        }

        @Override
        public Class<? extends OffsetableObject> getDTOClass() {
            return MerchantInventoryItemsObject.class;
        }
    };

    private static final Logger LOGGER = Logger.getLogger(MerchantInventoryItemsTableService.class);

    public List<ObjectTuple> getInventoryItemIdsByMerchantName(Integer merchantId) {
        ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();
        Dao<MerchantInventoryItemsObject, ?> dao;
        try {
            dao = DaoManager.createDao(connectionSource, MerchantInventoryItemsObject.class);
            GenericRawResults<Integer> rawResults =
                    dao.queryRaw(
                            "select itemId from merchant_inventory_items inner join merchant_inventory on merchant_inventory.inventoryId = merchant_inventory_items.inventoryId where merchant_inventory.merchantId = ?",
                            new RawRowMapper<Integer>() {
                                public Integer mapRow(String[] columnNames,
                                                     String[] resultColumns) {
                                    return Integer.valueOf(resultColumns[0]);
                                }
                            },
                            String.valueOf(merchantId));

            List<Integer> results = rawResults.getResults();
            Integer[] itemIds = results.toArray(new Integer[results.size()]);
            List<ItemPriceParametersObject> objects = ItemPriceParametersTableService.INSTANCE.getObjectByItemIds(itemIds);
            if (objects == null || objects.isEmpty()) {
                return Collections.emptyList();
            }

            Integer[] nameIds = new Integer[objects.size()];
            for (int i = 0; i < objects.size(); ++i) {
                nameIds[i] = objects.get(i).nameId;
            }

            return TextTableService.INSTANCE.getObjectTuples(nameIds, itemIds);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }
}
