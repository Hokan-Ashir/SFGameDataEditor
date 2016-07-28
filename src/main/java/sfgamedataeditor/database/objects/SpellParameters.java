package sfgamedataeditor.database.objects;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import sfgamedataeditor.fieldwrapping.Data;

@DatabaseTable(tableName = "spell_parameters")
public class SpellParameters extends OffsetableObject {

    @DatabaseField(generatedId = true)
    private Integer id;

    @Data(offset = 0, length = 2)
    @DatabaseField(canBeNull = false)
    private Integer spellNumber;

    @Data(offset = 2, length = 2)
    @DatabaseField(canBeNull = false)
    private Integer spellNameId;

    @Data(offset = 4, length = 1)
    @DatabaseField(canBeNull = false)
    private Integer requirementClass1;

    @Data(offset = 5, length = 1)
    @DatabaseField(canBeNull = false)
    private Integer requirementSubClass1;

    @Data(offset = 6, length = 1)
    @DatabaseField(canBeNull = false)
    private Integer requirementLevel1;

    @Data(offset = 7, length = 1)
    @DatabaseField(canBeNull = false)
    private Integer requirementClass2;

    @Data(offset = 8, length = 1)
    @DatabaseField(canBeNull = false)
    private Integer requirementSubClass2;

    @Data(offset = 9, length = 1)
    @DatabaseField(canBeNull = false)
    private Integer requirementLevel2;

    @Data(offset = 10, length = 1)
    @DatabaseField(canBeNull = false)
    private Integer requirementClass3;

    @Data(offset = 11, length = 1)
    @DatabaseField(canBeNull = false)
    private Integer requirementSubClass3;

    @Data(offset = 12, length = 1)
    @DatabaseField(canBeNull = false)
    private Integer requirementLevel3;

    // TODO add possible spell skill requirements (3 bytes offset)

    @Data(offset = 16, length = 2)
    @DatabaseField(canBeNull = false)
    private Integer manaUsage;

    @Data(offset = 18, length = 4)
    @DatabaseField(canBeNull = false)
    private Integer castTime;

    @Data(offset = 22, length = 4)
    @DatabaseField(canBeNull = false)
    private Integer cooldown;

    @Data(offset = 26, length = 2)
    @DatabaseField(canBeNull = false)
    private Integer minRange;

    @Data(offset = 28, length = 2)
    @DatabaseField(canBeNull = false)
    private Integer maxRange;

    @Data(offset = 30, length = 2)
    @DatabaseField(canBeNull = false)
    private Integer castType;

    @Data(offset = 32, length = 4)
    @DatabaseField(canBeNull = false)
    private Integer parameter1;

    @Data(offset = 36, length = 4)
    @DatabaseField(canBeNull = false)
    private Integer parameter2;

    @Data(offset = 40, length = 4)
    @DatabaseField(canBeNull = false)
    private Integer parameter3;

    @Data(offset = 44, length = 4)
    @DatabaseField(canBeNull = false)
    private Integer parameter4;

    @Data(offset = 48, length = 4)
    @DatabaseField(canBeNull = false)
    private Integer parameter5;

    @Data(offset = 52, length = 4)
    @DatabaseField(canBeNull = false)
    private Integer parameter6;

    @Data(offset = 56, length = 4)
    @DatabaseField(canBeNull = false)
    private Integer parameter7;

    @Data(offset = 60, length = 4)
    @DatabaseField(canBeNull = false)
    private Integer parameter8;

    @Data(offset = 64, length = 4)
    @DatabaseField(canBeNull = false)
    private Integer parameter9;

    public SpellParameters() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSpellNumber() {
        return spellNumber;
    }

    public void setSpellNumber(Integer spellNumber) {
        this.spellNumber = spellNumber;
    }

    public Integer getSpellNameId() {
        return spellNameId;
    }

    public void setSpellNameId(Integer spellNameId) {
        this.spellNameId = spellNameId;
    }

    public Integer getRequirementClass1() {
        return requirementClass1;
    }

    public void setRequirementClass1(Integer requirementClass1) {
        this.requirementClass1 = requirementClass1;
    }

    public Integer getRequirementSubClass1() {
        return requirementSubClass1;
    }

    public void setRequirementSubClass1(Integer requirementSubClass1) {
        this.requirementSubClass1 = requirementSubClass1;
    }

    public Integer getRequirementLevel1() {
        return requirementLevel1;
    }

    public void setRequirementLevel1(Integer requirementLevel1) {
        this.requirementLevel1 = requirementLevel1;
    }

    public Integer getRequirementClass2() {
        return requirementClass2;
    }

    public void setRequirementClass2(Integer requirementClass2) {
        this.requirementClass2 = requirementClass2;
    }

    public Integer getRequirementSubClass2() {
        return requirementSubClass2;
    }

    public void setRequirementSubClass2(Integer requirementSubClass2) {
        this.requirementSubClass2 = requirementSubClass2;
    }

    public Integer getRequirementLevel2() {
        return requirementLevel2;
    }

    public void setRequirementLevel2(Integer requirementLevel2) {
        this.requirementLevel2 = requirementLevel2;
    }

    public Integer getRequirementClass3() {
        return requirementClass3;
    }

    public void setRequirementClass3(Integer requirementClass3) {
        this.requirementClass3 = requirementClass3;
    }

    public Integer getRequirementSubClass3() {
        return requirementSubClass3;
    }

    public void setRequirementSubClass3(Integer requirementSubClass3) {
        this.requirementSubClass3 = requirementSubClass3;
    }

    public Integer getRequirementLevel3() {
        return requirementLevel3;
    }

    public void setRequirementLevel3(Integer requirementLevel3) {
        this.requirementLevel3 = requirementLevel3;
    }

    public Integer getManaUsage() {
        return manaUsage;
    }

    public void setManaUsage(Integer manaUsage) {
        this.manaUsage = manaUsage;
    }

    public Integer getCastTime() {
        return castTime;
    }

    public void setCastTime(Integer castTime) {
        this.castTime = castTime;
    }

    public Integer getCooldown() {
        return cooldown;
    }

    public void setCooldown(Integer cooldown) {
        this.cooldown = cooldown;
    }

    public Integer getMinRange() {
        return minRange;
    }

    public void setMinRange(Integer minRange) {
        this.minRange = minRange;
    }

    public Integer getMaxRange() {
        return maxRange;
    }

    public void setMaxRange(Integer maxRange) {
        this.maxRange = maxRange;
    }

    public Integer getCastType() {
        return castType;
    }

    public void setCastType(Integer castType) {
        this.castType = castType;
    }

    public Integer getParameter1() {
        return parameter1;
    }

    public void setParameter1(Integer parameter1) {
        this.parameter1 = parameter1;
    }

    public Integer getParameter2() {
        return parameter2;
    }

    public void setParameter2(Integer parameter2) {
        this.parameter2 = parameter2;
    }

    public Integer getParameter3() {
        return parameter3;
    }

    public void setParameter3(Integer parameter3) {
        this.parameter3 = parameter3;
    }

    public Integer getParameter4() {
        return parameter4;
    }

    public void setParameter4(Integer parameter4) {
        this.parameter4 = parameter4;
    }

    public Integer getParameter5() {
        return parameter5;
    }

    public void setParameter5(Integer parameter5) {
        this.parameter5 = parameter5;
    }

    public Integer getParameter6() {
        return parameter6;
    }

    public void setParameter6(Integer parameter6) {
        this.parameter6 = parameter6;
    }

    public Integer getParameter7() {
        return parameter7;
    }

    public void setParameter7(Integer parameter7) {
        this.parameter7 = parameter7;
    }

    public Integer getParameter8() {
        return parameter8;
    }

    public void setParameter8(Integer parameter8) {
        this.parameter8 = parameter8;
    }

    public Integer getParameter9() {
        return parameter9;
    }

    public void setParameter9(Integer parameter9) {
        this.parameter9 = parameter9;
    }
}
