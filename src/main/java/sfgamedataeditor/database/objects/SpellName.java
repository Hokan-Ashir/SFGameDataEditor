package sfgamedataeditor.database.objects;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "spell_name")
public class SpellName {

    @DatabaseField(id = true)
    public Integer spellType;

    @DatabaseField(canBeNull = false)
    public String name;
    
    @DatabaseField(canBeNull = false)
    private String field1;

    @DatabaseField(canBeNull = false)
    private String field2;

    @DatabaseField(canBeNull = false)
    private String field3;

    @DatabaseField(canBeNull = false)
    private String field4;

    @DatabaseField(canBeNull = false)
    private String field5;

    @DatabaseField(canBeNull = false)
    private String field6;

    @DatabaseField(canBeNull = false)
    private String field7;

    @DatabaseField(canBeNull = false)
    private String field8;

    @DatabaseField(canBeNull = false)
    private String field9;

    public SpellName() {
    }

    public SpellName(Integer spellType, String name) {
        this.spellType = spellType;
        this.name = name;
    }
}
