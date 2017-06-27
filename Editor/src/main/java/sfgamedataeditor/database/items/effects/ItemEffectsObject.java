package sfgamedataeditor.database.items.effects;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import sfgamedataeditor.database.common.Data;
import sfgamedataeditor.database.common.OffsetableObject;

@SuppressWarnings("unused")
@DatabaseTable(tableName = "item_effects")
public class ItemEffectsObject extends OffsetableObject {

    // offsets taken from http://spellforcefanforum.hostoi.com/viewtopic.php?f=29&t=272&sid=c36d0fbf6779c51b97a2e021ad97cc44

    @Data(offset = 0, length = 2)
    @DatabaseField(id = true)
    private Integer itemId;

    @Data(offset = 2, length = 2)
    @DatabaseField
    private Integer effectNumber;
}
