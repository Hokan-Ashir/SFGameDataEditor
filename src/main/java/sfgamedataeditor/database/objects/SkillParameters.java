package sfgamedataeditor.database.objects;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "skill_parameters")
public class SkillParameters {

    @DatabaseField(generatedId = true)
    private Integer id;

//    TODO FK on SkillName
    @Data
    @DatabaseField(canBeNull = false)
    private Integer skillNameId;

    @Data
    @DatabaseField(canBeNull = false)
    private Integer strengthRequired;

    @Data
    @DatabaseField(canBeNull = false)
    private Integer staminaRequired;

    @Data
    @DatabaseField(canBeNull = false)
    private Integer agilityRequired;

    @Data
    @DatabaseField(canBeNull = false)
    private Integer dexterityRequired;

    @Data
    @DatabaseField(canBeNull = false)
    private Integer charismaRequired;

    @Data
    @DatabaseField(canBeNull = false)
    private Integer intelligenceRequired;

    @Data
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

    public Integer getSkillNameId() {
        return skillNameId;
    }

    public void setSkillNameId(Integer skillNameId) {
        this.skillNameId = skillNameId;
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
}
