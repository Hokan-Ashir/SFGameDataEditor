package sfgamedataeditor.database.items.price.parameters;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import sfgamedataeditor.database.common.Data;
import sfgamedataeditor.database.common.OffsetableObject;

@SuppressWarnings("unused")
@DatabaseTable(tableName = "item_price_parameters")
public class ItemPriceParametersObject extends OffsetableObject {

    // offsets taken from http://spellforcefanforum.hostoi.com/viewtopic.php?f=29&t=259&sid=c36d0fbf6779c51b97a2e021ad97cc44

    @DatabaseField(generatedId = true)
    private Integer id;

    @Data(offset = 0, length = 2)
    @DatabaseField
    public Integer itemId;

    //    helmet 01 01
    //    upper armor 01 02
    //    lower armor 01 03
    //    ring 01 06
    //    1H weapon 01 07
    //    2H weapon 01 08
    //    shield 01 09
    //    robe/full body armor 01 0A
    //    figure NPC/enemy/animal 01 0B
    //    xbow/bow 01 0C
    //    figure hero army units 01 0D
    //    rune hero in inventory 02 00
    //    rune workers in inventory 02 0X (X=1-6 race type)
    //    rune hero added 03 00
    //    rune workers added 03 0X (X=1-6 race type)
    //    scroll 04 00
    //    spell 05 00
    //    unit plan in inventory 06 0X (X=1-6 race type)
    //    building plan in inventory 07 0X (X=1-6 race type)
    //    unit plan added 08 0X (X=1-6 race type)
    //    building plan added 09 0X (X=1-6 race type)
    //    quest item or jewelry 0A 00
    //    blank scrolls lvl 1-20 0B 00
    @Data(offset = 2, length = 2)
    @DatabaseField
    public Integer typeId;

    @Data(offset = 4, length = 2)
    @DatabaseField
    public Integer nameId;

    @Data(offset = 6, length = 2)
    @DatabaseField
    public Integer unitStatsId;

    @Data(offset = 8, length = 2)
    @DatabaseField
    public Integer armyStatsId;

    @Data(offset = 10, length = 2)
    @DatabaseField
    private Integer buildingId;

    // TODO one byte of unknown parameters (possible values are 0, 4, 8, 16)
    // still have to be serialized, cause during deserialization whole object is deserialized into byte array
    // and no bytes should be replaced by zeros
    @Data(offset = 12, length = 1)
    @DatabaseField
    private Integer unknown;

    @Data(offset = 13, length = 4)
    @DatabaseField
    private Integer copperSellingPrice;

    @Data(offset = 17, length = 4)
    @DatabaseField
    private Integer copperBuyingPrice;

    @Data(offset = 21, length = 1)
    @DatabaseField
    private Integer itemSetId;
}
