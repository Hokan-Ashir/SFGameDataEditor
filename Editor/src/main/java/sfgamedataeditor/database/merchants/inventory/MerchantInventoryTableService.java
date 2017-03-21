package sfgamedataeditor.database.merchants.inventory;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import org.apache.log4j.Logger;
import sfgamedataeditor.database.common.CommonTableService;
import sfgamedataeditor.database.common.OffsetableObject;
import sfgamedataeditor.database.common.TableCreationService;
import sfgamedataeditor.database.merchants.items.MerchantInventoryItemsTableService;
import sfgamedataeditor.views.utility.Pair;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public enum MerchantInventoryTableService implements TableCreationService {
    INSTANCE {
        @Override
        public void createTable() {
            CommonTableService.INSTANCE.recreateTable(MerchantInventoryObject.class);
        }

        @Override
        public void addRecordsToTable(List<Pair<byte[], Long>> offsettedData) {
            CommonTableService.INSTANCE.addRecordsToTable(MerchantInventoryObject.class, offsettedData);
        }

        @Override
        public int getDataLength() {
            return 0x4;
        }

        @Override
        public Pair<Integer, Integer> getOffsetInterval() {
            return new Pair<>(0x03F864CC, 0x03F867CB);
        }

        @Override
        public Class<? extends OffsetableObject> getDTOClass() {
            return MerchantInventoryObject.class;
        }
    };

    private static final Logger LOGGER = Logger.getLogger(MerchantInventoryTableService.class);

    public String getMerchantNameByItemId(List<Integer> itemIds) {
        ConnectionSource connectionSource = CommonTableService.INSTANCE.getConnectionSource();
        Dao<MerchantInventoryObject, ?> dao;
        try {
            dao = DaoManager.createDao(connectionSource, MerchantInventoryObject.class);
            Integer inventoryId = MerchantInventoryItemsTableService.INSTANCE.getMerchantInventoryIdByInventoryItemIds(itemIds);
            List<MerchantInventoryObject> merchants = dao.queryBuilder().where().eq("inventoryId", inventoryId).query();

            String merchantId = String.valueOf(merchants.get(0).merchantId);
            return I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, merchantId);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    public Set<String> getAllMerchantNames() {
        List<MerchantInventoryObject> allTableData = CommonTableService.INSTANCE.getAllTableData(MerchantInventoryObject.class);
        Set<String> names = new TreeSet<>();
        for (MerchantInventoryObject merchantInventoryObject : allTableData) {
            names.add(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES, String.valueOf(merchantInventoryObject.merchantId)));
        }

        return names;
    }
}
