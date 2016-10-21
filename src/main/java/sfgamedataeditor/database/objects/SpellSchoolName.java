package sfgamedataeditor.database.objects;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "spell_school_name")
public class SpellSchoolName {

    @DatabaseField(id = true)
    public String name;

    public SpellSchoolName() {
    }

    public SpellSchoolName(String name) {
        this.name = name;
    }
}
