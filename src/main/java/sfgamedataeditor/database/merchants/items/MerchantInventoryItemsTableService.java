package sfgamedataeditor.database.merchants.items;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.dao.RawRowMapper;
import com.j256.ormlite.support.ConnectionSource;
import org.apache.log4j.Logger;
import sfgamedataeditor.database.common.CommonTableService;
import sfgamedataeditor.views.utility.Pair;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public enum MerchantInventoryItemsTableService {
    INSTANCE;

    private static final Logger LOGGER = Logger.getLogger(MerchantInventoryItemsTableService.class);

    public void createMerchantInventoryItemsTable() {
        CommonTableService.INSTANCE.recreateTable(MerchantInventoryItemsObject.class);
    }

    public void addRecordsToMerchantInventoryItemsTable(List<Pair<byte[], Long>> offsettedData) {
        CommonTableService.INSTANCE.addRecordsToTable(MerchantInventoryItemsObject.class, offsettedData);
    }

    public List<Integer> getInventoryItemIdsByMerchantName(String merchantName) {
        int merchantId = getMerchantId(merchantName);
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

    private int getMerchantId(String merchantName) {
        ResourceBundle bundle = I18NService.INSTANCE.getBundle(I18NTypes.CREATURES);
        int merchantId = 0;
        for (String key : bundle.keySet()) {
            if (bundle.getString(key).equals(merchantName)) {
                merchantId = Integer.parseInt(key);
                break;
            }
        }

        return merchantId;
    }
}
