package sfgamedataeditor.database.objects;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "spell_name")
public class SpellName {

    @DatabaseField(id = true)
    public Integer spellType;

    @DatabaseField(canBeNull = false)
    public String name;
    
    @DatabaseField
    private String field1;

    @DatabaseField
    private String field2;

    @DatabaseField
    private String field3;

    @DatabaseField
    private String field4;

    @DatabaseField
    private String field5;

    @DatabaseField
    private String field6;

    @DatabaseField
    private String field7;

    @DatabaseField
    private String field8;

    @DatabaseField
    private String field9;

    public SpellName() {
    }

    public SpellName(Integer spellType, String name) {
        this.spellType = spellType;
        this.name = name;
    }
}
