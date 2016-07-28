package sfgamedataeditor.database.objects;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "spell_name")
public class SpellName {

    @DatabaseField(id = true)
    private Integer spellType;

    @DatabaseField(canBeNull = false)
    private String name;

    public SpellName() {
    }

    public Integer getSpellType() {
        return spellType;
    }

    public String getName() {
        return name;
    }

    public SpellName(Integer spellType, String name) {
        this.spellType = spellType;
        this.name = name;
    }
}
