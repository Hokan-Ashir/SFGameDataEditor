package sfgamedataeditor.database.objects;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "skill_name")
public class SkillName {

    @DatabaseField(id = true)
    private Integer skillType_id;

    @DatabaseField(canBeNull = false)
    private String name;

    public SkillName() {
    }

    public Integer getSkillType() {
        return skillType_id;
    }

    public String getName() {
        return name;
    }

    public SkillName(Integer skillType, String name) {
        this.skillType_id = skillType;
        this.name = name;
    }
}
