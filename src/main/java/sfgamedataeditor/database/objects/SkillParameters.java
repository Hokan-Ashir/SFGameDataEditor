package sfgamedataeditor.database.objects;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import sfgamedataeditor.fieldwrapping.Data;

@DatabaseTable(tableName = "skill_parameters")
public class SkillParameters {

    @DatabaseField(generatedId = true)
    private Integer id;

//    TODO FK on SkillName
    @Data(offset = 0, length = 1)
    @DatabaseField(canBeNull = false, foreign = true/*, foreignColumnName = SkillName.SKILL_TYPE_COLUMN_NAME*/)
    private SkillName skillType_id;

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

    @DatabaseField(canBeNull = false)
    private Long offsetInFile;

    public SkillParameters() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SkillName getSkillType() {
        return skillType_id;
    }

    public void setSkillType(SkillName skillType) {
        this.skillType_id = skillType;
    }

    public Integer getStrengthRequired() {
        return strengthRequired;
    }

    public void setStrengthRequired(Integer strengthRequired) {
        this.strengthRequired = strengthRequired;
    }

    public Integer getStaminaRequired() {
        return staminaRequired;
    }

    public void setStaminaRequired(Integer staminaRequired) {
        this.staminaRequired = staminaRequired;
    }

    public Integer getAgilityRequired() {
        return agilityRequired;
    }

    public void setAgilityRequired(Integer agilityRequired) {
        this.agilityRequired = agilityRequired;
    }

    public Integer getDexterityRequired() {
        return dexterityRequired;
    }

    public void setDexterityRequired(Integer dexterityRequired) {
        this.dexterityRequired = dexterityRequired;
    }

    public Integer getCharismaRequired() {
        return charismaRequired;
    }

    public void setCharismaRequired(Integer charismaRequired) {
        this.charismaRequired = charismaRequired;
    }

    public Integer getIntelligenceRequired() {
        return intelligenceRequired;
    }

    public void setIntelligenceRequired(Integer intelligenceRequired) {
        this.intelligenceRequired = intelligenceRequired;
    }

    public Integer getWisdomRequired() {
        return wisdomRequired;
    }

    public void setWisdomRequired(Integer wisdomRequired) {
        this.wisdomRequired = wisdomRequired;
    }

    public Long getOffsetInFile() {
        return offsetInFile;
    }

    public void setOffsetInFile(Long offsetInFile) {
        this.offsetInFile = offsetInFile;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
