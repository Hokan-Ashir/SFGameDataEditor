package sfgamedataeditor.database.objects;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "skill_parameters")
public class SkillParameters extends OffsetableObject {

    @DatabaseField(generatedId = true)
    private Integer id;

    @Data(offset = 0, length = 1)
    @DatabaseField(canBeNull = false)
    private Integer skillTypeId;

    @Data(offset = 1, length = 1)
    @DatabaseField(canBeNull = false)
    private Integer level;

    @Data(offset = 2, length = 1)
    @DatabaseField(canBeNull = false)
    private Integer strengthRequired;

    @Data(offset = 3, length = 1)
    @DatabaseField(canBeNull = false)
    private Integer staminaRequired;

    @Data(offset = 4, length = 1)
    @DatabaseField(canBeNull = false)
    private Integer agilityRequired;

    @Data(offset = 5, length = 1)
    @DatabaseField(canBeNull = false)
    private Integer dexterityRequired;

    @Data(offset = 6, length = 1)
    @DatabaseField(canBeNull = false)
    private Integer charismaRequired;

    @Data(offset = 7, length = 1)
    @DatabaseField(canBeNull = false)
    private Integer intelligenceRequired;

    @Data(offset = 8, length = 1)
    @DatabaseField(canBeNull = false)
    private Integer wisdomRequired;

    public SkillParameters() {
    }
}
