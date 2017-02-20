package sfgamedataeditor.database.creatures.spells;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import sfgamedataeditor.database.common.Data;
import sfgamedataeditor.database.common.OffsetableObject;

@DatabaseTable(tableName = "creature_spells")
public class CreatureSpellObject extends OffsetableObject {

    // offsets are taken from http://spellforcefanforum.hostoi.com/viewtopic.php?f=29&t=258&sid=c36d0fbf6779c51b97a2e021ad97cc44

    @DatabaseField(generatedId = true)
    private Integer id;

    @Data(offset = 0, length = 2)
    @DatabaseField
    public Integer creatureId;

    // (hero can have 01 - 0A; like your char)
    @Data(offset = 2, length = 1)
    @DatabaseField
    public Integer skillNumber;

    @Data(offset = 3, length = 2)
    @DatabaseField
    public Integer spellNumber;
}
