package sfgamedataeditor.database.creatures.equipment;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import sfgamedataeditor.database.common.Data;
import sfgamedataeditor.database.common.OffsetableObject;

@DatabaseTable(tableName = "creature_equipment")
public class CreatureEquipmentObject extends OffsetableObject {

    @DatabaseField(generatedId = true)
    private Integer id;

    @Data(offset = 0, length = 2)
    @DatabaseField
    private Integer creatureId;

    //    00 - helmet
    //    01 - right hand
    //    02 - chest
    //    03 - left hand
    //    04 - right ring
    //    05 - legs
    //    06 - left ring
    @Data(offset = 2, length = 1)
    @DatabaseField
    private Integer equipmentSlot;

    @Data(offset = 3, length = 2)
    @DatabaseField
    private Integer itemId;
}
