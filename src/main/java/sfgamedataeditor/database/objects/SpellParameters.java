package sfgamedataeditor.database.objects;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import sfgamedataeditor.fieldwrapping.Data;

@DatabaseTable(tableName = "spell_parameters")
public class SpellParameters extends OffsetableObject {

    @DatabaseField(generatedId = true)
    public Integer id;

    @Data(offset = 0, length = 2)
    @DatabaseField(canBeNull = false)
    public Integer spellNumber;

    @Data(offset = 2, length = 2)
    @DatabaseField(canBeNull = false)
    public Integer spellNameId;

    @Data(offset = 4, length = 1)
    @DatabaseField(canBeNull = false)
    public Integer requirementClass1;

    @Data(offset = 5, length = 1)
    @DatabaseField(canBeNull = false)
    public Integer requirementSubClass1;

    @Data(offset = 6, length = 1)
    @DatabaseField(canBeNull = false)
    public Integer requirementLevel1;

    @Data(offset = 7, length = 1)
    @DatabaseField(canBeNull = false)
    public Integer requirementClass2;

    @Data(offset = 8, length = 1)
    @DatabaseField(canBeNull = false)
    public Integer requirementSubClass2;

    @Data(offset = 9, length = 1)
    @DatabaseField(canBeNull = false)
    public Integer requirementLevel2;

    @Data(offset = 10, length = 1)
    @DatabaseField(canBeNull = false)
    public Integer requirementClass3;

    @Data(offset = 11, length = 1)
    @DatabaseField(canBeNull = false)
    public Integer requirementSubClass3;

    @Data(offset = 12, length = 1)
    @DatabaseField(canBeNull = false)
    public Integer requirementLevel3;

    // TODO add possible spell skill requirements (3 bytes offset)

    @Data(offset = 16, length = 2)
    @DatabaseField(canBeNull = false)
    public Integer manaUsage;

    @Data(offset = 18, length = 4)
    @DatabaseField(canBeNull = false)
    public Integer castTime;

    @Data(offset = 22, length = 4)
    @DatabaseField(canBeNull = false)
    public Integer cooldown;

    @Data(offset = 26, length = 2)
    @DatabaseField(canBeNull = false)
    public Integer minRange;

    @Data(offset = 28, length = 2)
    @DatabaseField(canBeNull = false)
    public Integer maxRange;

    @Data(offset = 30, length = 2)
    @DatabaseField(canBeNull = false)
    public Integer castType;

    @Data(offset = 32, length = 4)
    @DatabaseField(canBeNull = false)
    public Integer parameter1;

    @Data(offset = 36, length = 4)
    @DatabaseField(canBeNull = false)
    public Integer parameter2;

    @Data(offset = 40, length = 4)
    @DatabaseField(canBeNull = false)
    public Integer parameter3;

    @Data(offset = 44, length = 4)
    @DatabaseField(canBeNull = false)
    public Integer parameter4;

    @Data(offset = 48, length = 4)
    @DatabaseField(canBeNull = false)
    public Integer parameter5;

    @Data(offset = 52, length = 4)
    @DatabaseField(canBeNull = false)
    public Integer parameter6;

    @Data(offset = 56, length = 4)
    @DatabaseField(canBeNull = false)
    public Integer parameter7;

    @Data(offset = 60, length = 4)
    @DatabaseField(canBeNull = false)
    public Integer parameter8;

    @Data(offset = 64, length = 4)
    @DatabaseField(canBeNull = false)
    public Integer parameter9;

    public SpellParameters() {
    }
}
