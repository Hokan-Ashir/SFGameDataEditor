package sfgamedataeditor.database.items.weapon.parameters;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import sfgamedataeditor.database.common.Data;
import sfgamedataeditor.database.common.OffsetableObject;

@SuppressWarnings("unused")
@DatabaseTable(tableName = "weapon_parameters")
public class WeaponParametersObject extends OffsetableObject {

    // offsets taken from http://spellforcefanforum.hostoi.com/viewtopic.php?f=29&t=268&sid=c36d0fbf6779c51b97a2e021ad97cc44

    @DatabaseField(generatedId = true)
    private Integer id;

    @Data(offset = 0, length = 2)
    @DatabaseField
    public Integer itemId;

    @Data(offset = 2, length = 2)
    @DatabaseField
    private Integer minDamage;

    @Data(offset = 4, length = 2)
    @DatabaseField
    private Integer maxDamage;

    @Data(offset = 6, length = 2)
    @DatabaseField
    private Integer minRange;

    @Data(offset = 8, length = 2)
    @DatabaseField
    private Integer maxRange;

    @Data(offset = 10, length = 2)
    @DatabaseField
    private Integer speed;

    //    01 00 - default weapon type
    //    02 00 - Weapon Type Hand
    //    03 00 - Weapon Type 1H Dagger
    //    04 00 - Weapon Type 1H Sword
    //    05 00 - Weapon Type 1H Axe
    //    06 00 - Weapon Type 1H Mace Spiky
    //    07 00 - Weapon Type 1H Hammer
    //    08 00 - Weapon Type 1H Staff
    //    09 00 - Weapon Type 2H Sword
    //    0A 00 - Weapon Type 2H Axe
    //    0B 00 - Weapon Type 2H Mace
    //    0C 00 - Weapon Type 2H Hammer
    //    0D 00 - Weapon Type 2H Staff
    //    0E 00 - Weapon Type 2H Spear
    //    0F 00 - Weapon Type 2H Halberd
    //    10 00 - Weapon Type 2H Bow
    //    11 00 - Weapon Type 2H Crossbow
    //    12 00 - Weapon Type 1H Mace Blunt
    //    13 00 - Weapon Type Claw
    //    14 00 - Weapon Type Mouth
    //    15 00 - Weapon Type Stone Thrower
    //    16 00 - Weapon Type Stone Thrower
    @Data(offset = 12, length = 2)
    @DatabaseField
    public Integer type;

    //    01 00 - default weapon material
    //    02 00 - Weapon Material Fist
    //    03 00 - Weapon Material Wood
    //    04 00 - Weapon Material Stone
    //    05 00 - Weapon Material Metal
    //    06 00 - Weapon Material Magic Metal
    //    07 00 - Weapon Material Bone
    @Data(offset = 14, length = 2)
    @DatabaseField
    private Integer material;
}
