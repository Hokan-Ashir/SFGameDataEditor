package sfgamedataeditor.database.merchants.inventory;

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
import java.util.List;

public enum MerchantInventoryTableService {
    INSTANCE;

    private static final Logger LOGGER = Logger.getLogger(MerchantInventoryTableService.class);

    public void createMerchantInventoryTable() {
        CommonTableService.INSTANCE.recreateTable(MerchantInventoryObject.class);
    }

    public void addRecordsToMerchantInventoryTable(List<Pair<byte[], Long>> offsettedData) {
        CommonTableService.INSTANCE.addRecordsToTable(MerchantInventoryObject.class, offsettedData);
    }

    public String getMerchantNameByItemId(Integer itemId) {
        ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();
        Dao<MerchantInventoryObject, ?> dao;
        try {
            dao = DaoManager.createDao(connectionSource, MerchantInventoryObject.class);
            GenericRawResults<String> rawResults =
                    dao.queryRaw(
                            "select merchantId from merchant_inventory inner join merchant_inventory_items on merchant_inventory.inventoryId = merchant_inventory_items.inventoryId where merchant_inventory_items.itemId = ?",
                            new RawRowMapper<String>() {
                                public String mapRow(String[] columnNames,
                                                     String[] resultColumns) {
                                    return resultColumns[0];
                                }
                            },
                            String.valueOf(itemId));

            String merchantId = rawResults.getResults().get(0);
            return I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, merchantId);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }
}
