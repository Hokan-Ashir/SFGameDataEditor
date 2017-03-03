package sfgamedataeditor.database.merchants.inventory;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import sfgamedataeditor.database.common.Data;
import sfgamedataeditor.database.common.OffsetableObject;

@SuppressWarnings("unused")
@DatabaseTable(tableName = "merchant_inventory")
public class MerchantInventoryObject extends OffsetableObject {

    @DatabaseField(generatedId = true)
    private Integer id;

    @Data(offset = 0, length = 2)
    @DatabaseField(canBeNull = false)
    private Integer inventoryId;

    @Data(offset = 2, length = 2)
    @DatabaseField(canBeNull = false)
    public Integer merchantId;

    public MerchantInventoryObject() {
    }
}
