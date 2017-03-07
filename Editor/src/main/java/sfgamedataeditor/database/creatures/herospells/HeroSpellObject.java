package sfgamedataeditor.database.creatures.herospells;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import sfgamedataeditor.database.common.Data;
import sfgamedataeditor.database.common.OffsetableObject;

@SuppressWarnings("unused")
@DatabaseTable(tableName = "hero_spells")
public class HeroSpellObject extends OffsetableObject {

    // offsets are taken from http://spellforcefanforum.hostoi.com/viewtopic.php?f=29&t=258

    @DatabaseField(generatedId = true)
    private Integer id;

    @Data(offset = 0, length = 2)
    @DatabaseField
    public Integer statsId;

    // (hero can have 01 - 0A; like your char)
    @Data(offset = 2, length = 1)
    @DatabaseField
    public Integer skillNumber;

    @Data(offset = 3, length = 2)
    @DatabaseField
    public Integer spellNumber;
}
