package sfgamedataeditor.database.objects;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "skill_name")
public class SkillName {

    @DatabaseField(id = true)
    private Integer skillType;

    @DatabaseField(canBeNull = false)
    private String name;

    public SkillName() {
    }

    public SkillName(Integer skillType, String name) {
        this.skillType = skillType;
        this.name = name;
    }
}
