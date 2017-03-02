package sfgamedataeditor.database.merchants.items;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.dao.RawRowMapper;
import com.j256.ormlite.stmt.Where;
import com.j256.ormlite.support.ConnectionSource;
import org.apache.log4j.Logger;
import sfgamedataeditor.database.common.CommonTableService;
import sfgamedataeditor.database.common.TableCreationService;
import sfgamedataeditor.database.creatures.common.CreatureCommonParametersTableService;
import sfgamedataeditor.dataextraction.DTOOffsetTypes;
import sfgamedataeditor.views.utility.Pair;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public enum MerchantInventoryItemsTableService implements TableCreationService {
    INSTANCE {
        @Override
        public void createTable() {
            CommonTableService.INSTANCE.recreateTable(MerchantInventoryItemsObject.class);
        }

        @Override
        public void addRecordsToTable(List<Pair<byte[], Long>> offsettedData) {
            CommonTableService.INSTANCE.addRecordsToTable(MerchantInventoryItemsObject.class, offsettedData);
        }

        @Override
        public DTOOffsetTypes getDTOOffsetType() {
            return DTOOffsetTypes.MERCHANT_INVENTORY_ITEMS;
        }
    };

    private static final Logger LOGGER = Logger.getLogger(MerchantInventoryItemsTableService.class);

    public List<Integer> getInventoryItemIdsByMerchantName(String merchantName) {
        Integer merchantId = CreatureCommonParametersTableService.INSTANCE.getCreatureIdByName(merchantName);
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

            return rawResults.getResults();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    public Integer getMerchantInventoryIdByInventoryItemIds(List<Integer> itemIds) {
        ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();
        Dao<MerchantInventoryItemsObject, Integer> dao;
        try {
            dao = DaoManager.createDao(connectionSource, MerchantInventoryItemsObject.class);
            Where<MerchantInventoryItemsObject, Integer> where = dao.queryBuilder().where().in("itemId", itemIds);
            String query = where.getStatement();
            query = "select distinct inventoryId from merchant_inventory_items where " + query + " group by inventoryId having count(inventoryId) = ?";
            GenericRawResults<Integer> rawResults =
                    dao.queryRaw(
                            query,
                            new RawRowMapper<Integer>() {
                                public Integer mapRow(String[] columnNames,
                                                      String[] resultColumns) {
                                    return Integer.valueOf(resultColumns[0]);
                                }
                            }, String.valueOf(itemIds.size()));

            for (Integer inventoryId : rawResults) {
                long numberOfSellingItems = dao.queryBuilder().where().eq("inventoryId", inventoryId).countOf();
                if (numberOfSellingItems == itemIds.size()) {
                    return inventoryId;
                }
            }

            return null;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }
}
