package sfgamedataeditor.database.creatures.skills;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import sfgamedataeditor.database.common.Data;
import sfgamedataeditor.database.common.OffsetableObject;

@SuppressWarnings("unused")
@DatabaseTable(tableName = "creature_skills")
public class CreatureSkillObject extends OffsetableObject {

    // offsets are taken from http://spellforcefanforum.hostoi.com/viewtopic.php?f=29&t=257&sid=c36d0fbf6779c51b97a2e021ad97cc44

    @DatabaseField(generatedId = true)
    private Integer id;

    @Data(offset = 0, length = 2)
    @DatabaseField
    public Integer creatureStatsId;

    //    01 - light combat arts
    //    00 - no sub school
    //    01 - piercing
    //    02 - blade
    //    03 - blunt
    //    04 - armor
    //    05 - no text
    //    06 - no text
    //    02 - heavy combat arts
    //    00 - no sub school
    //    01 - blade
    //    02 - blunt
    //    03 - armor
    //    04 - shield
    //    03 - ranged combat arts
    //    00 - no sub school
    //    01 - bow
    //    02 - crossbow
    //    03 - no text
    //    04 - no text
    //    04 - white magic
    //    00 - no sub school
    //    01 - life
    //    02 - nature
    //    03 - boons
    //    04 - no text
    //    05 - elemental magic
    //    00 - no sub school
    //    01 - fire
    //    02 - ice
    //    03 - earth
    //    04 - no text
    //    06 - mind magic
    //    00 - no sub school
    //    01 - enchantment
    //    02 - offensive
    //    03 - defensive
    //    04 - no text
    //    07 - black magic
    //    00 - no sub school
    //    01 - death
    //    02 - necromancy
    //    03 - curse
    //    04 - no text
    @Data(offset = 2, length = 1)
    @DatabaseField
    public Integer skillSchoolClass;

    // for workers my (author of post) guess:
    //    00 01 01 - wood gathering skill
    //    00 03 01 - stone gathering skill
    //    00 08 01 - iron gathering skill
    //    00 09 01 - humans unique skill
    //    00 0A 01 - elves unique skill
    //    00 0B 01 - dwarves unique skill
    //    00 0C 01 - orcs unique skill
    //    00 0D 01 - trolls unique skill
    //    00 0E 01 - darkelves unique skill
    //
    //    00 00 01 - level
    //    00 06 01 - don't have any unit
    @Data(offset = 3, length = 1)
    @DatabaseField
    public Integer skillSchoolSubClass;

    @Data(offset = 4, length = 1)
    @DatabaseField
    public Integer skillLevel;
}
