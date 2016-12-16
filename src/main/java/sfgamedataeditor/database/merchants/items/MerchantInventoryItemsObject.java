package sfgamedataeditor.database.merchants.items;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import sfgamedataeditor.database.common.Data;
import sfgamedataeditor.database.common.OffsetableObject;

@DatabaseTable(tableName = "merchant_inventory_items")
public class MerchantInventoryItemsObject extends OffsetableObject {

    @DatabaseField(generatedId = true)
    private Integer id;

    @Data(offset = 0, length = 2)
    @DatabaseField(canBeNull = false)
    private Integer inventoryId;

    @Data(offset = 2, length = 2)
    @DatabaseField(canBeNull = false)
    public Integer itemId;

    @Data(offset = 4, length = 2)
    @DatabaseField(canBeNull = false)
    private Integer itemType;

    public MerchantInventoryItemsObject() {
    }
}
