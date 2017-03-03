package sfgamedataeditor.database.items.requirements;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import sfgamedataeditor.database.common.Data;
import sfgamedataeditor.database.common.OffsetableObject;

@SuppressWarnings("unused")
@DatabaseTable(tableName = "item_requirements")
public class ItemRequirementsObject extends OffsetableObject {

    // offsets taken from http://spellforcefanforum.hostoi.com/viewtopic.php?f=29&t=269&sid=c36d0fbf6779c51b97a2e021ad97cc44

    @DatabaseField(generatedId = true)
    private Integer id;

    @Data(offset = 0, length = 2)
    @DatabaseField
    private Integer itemId;

    @Data(offset = 2, length = 1)
    @DatabaseField
    private Integer requirementNumber;

    //    00 - no school (for char level requirement only)
    //    01 - light combat arts
    //    02 - heavy combat arts
    //    03 - ranged combat arts
    //    04 - white magic
    //    05 - elemental magic
    //    06 - mind magic
    //    07 - black magic
    @Data(offset = 3, length = 1)
    @DatabaseField
    private Integer schoolRequirementClass;


    //    00 - no sub school (for char level requirement only)
    //    LC:
    //            01 - piercing weapons
    //    02 - light blade weapons
    //    03 - light blunt weapons
    //    04 - light armor
    //    HC:
    //            01 - heavy blade weapons
    //    02 - heavy blunt weapons
    //    03 - heavy armor
    //    04 - shields
    //    RC:
    //            01 - bows
    //    02 - crossbows
    //    WM:
    //            01 - life
    //    02 - nature
    //    03 - boons
    //    EM:
    //            01 - fire
    //    02 - ice
    //    03 - earth
    //    MM:
    //            01 - enchantment
    //    02 - offensive
    //    03 - defensive
    //    BM:
    //            01 - death
    //    02 - necromancy
    //    03 - curse
    @Data(offset = 4, length = 1)
    @DatabaseField
    private Integer subSchoolRequirementClass;

    //    01 - 14 for combat arts/magic requirement
    //    01 - 32 for char level requirement
    @Data(offset = 5, length = 1)
    @DatabaseField
    private Integer level;
}
