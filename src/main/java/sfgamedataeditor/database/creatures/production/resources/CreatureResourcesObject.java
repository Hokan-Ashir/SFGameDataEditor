package sfgamedataeditor.database.creatures.production.resources;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import sfgamedataeditor.database.common.Data;
import sfgamedataeditor.database.common.OffsetableObject;

@DatabaseTable(tableName = "creature_resource")
public class CreatureResourcesObject extends OffsetableObject {

    // offsets taken from http://spellforcefanforum.hostoi.com/viewtopic.php?f=29&t=282&sid=c36d0fbf6779c51b97a2e021ad97cc44

    @DatabaseField(generatedId = true)
    private Integer id;

    @Data(offset = 0, length = 2)
    @DatabaseField
    private Integer creatureId;

    //    01 - wood
    //    02 - stone
    //    03 - log
    //    04 - moonsilver
    //    05 - food
    //    06 - berry
    //    07 - iron
    //    08 - tree
    //    09 - grain
    //    0B - fish
    //    0F - mushroom
    //    10 - meat
    //    12 - aria
    //    13 - lenya
    @Data(offset = 2, length = 1)
    @DatabaseField
    public Integer resourceId;

    @Data(offset = 3, length = 1)
    @DatabaseField
    private Integer resourceAmount;
}
