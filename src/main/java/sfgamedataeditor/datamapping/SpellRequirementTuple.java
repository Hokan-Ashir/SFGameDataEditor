package sfgamedataeditor.datamapping;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class SpellRequirementTuple {
    private Integer schoolRequirement1;
    private Integer subSchoolRequirement1;
    private Integer schoolRequirement2;
    private Integer subSchoolRequirement2;
    private Integer schoolRequirement3;
    private Integer subSchoolRequirement3;

    public SpellRequirementTuple(Integer schoolRequirement) {
        this.schoolRequirement1 = schoolRequirement;
    }

    public SpellRequirementTuple(Integer schoolRequirement, Integer subSchoolRequirement1) {
        this.schoolRequirement1 = schoolRequirement;
        this.subSchoolRequirement1 = subSchoolRequirement1;
    }

    public SpellRequirementTuple(Integer schoolRequirement1, Integer subSchoolRequirement1,
                                 Integer schoolRequirement2, Integer subSchoolRequirement2) {
        this.schoolRequirement1 = schoolRequirement1;
        this.subSchoolRequirement1 = subSchoolRequirement1;
        this.schoolRequirement2 = schoolRequirement2;
        this.subSchoolRequirement2 = subSchoolRequirement2;
    }

    public SpellRequirementTuple(Integer schoolRequirement1, Integer subSchoolRequirement1,
                                 Integer schoolRequirement2, Integer subSchoolRequirement2,
                                 Integer schoolRequirement3, Integer subSchoolRequirement3) {
        this.schoolRequirement1 = schoolRequirement1;
        this.subSchoolRequirement1 = subSchoolRequirement1;
        this.schoolRequirement2 = schoolRequirement2;
        this.subSchoolRequirement2 = subSchoolRequirement2;
        this.schoolRequirement3 = schoolRequirement3;
        this.subSchoolRequirement3 = subSchoolRequirement3;
    }

    public Integer getSchoolRequirement1() {
        return schoolRequirement1;
    }

    public Integer getSubSchoolRequirement1() {
        return subSchoolRequirement1;
    }

    public Integer getSchoolRequirement2() {
        return schoolRequirement2;
    }

    public Integer getSubSchoolRequirement2() {
        return subSchoolRequirement2;
    }

    public Integer getSchoolRequirement3() {
        return schoolRequirement3;
    }

    public Integer getSubSchoolRequirement3() {
        return subSchoolRequirement3;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(schoolRequirement1)
                .append(schoolRequirement2)
                .append(schoolRequirement3)
                .append(subSchoolRequirement1)
                .append(subSchoolRequirement2)
                .append(subSchoolRequirement3).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        if (obj.getClass() != getClass()) {
            return false;
        }

        SpellRequirementTuple other = (SpellRequirementTuple) obj;
        return new EqualsBuilder()
                .append(schoolRequirement1, other.getSchoolRequirement1())
                .append(schoolRequirement2, other.getSchoolRequirement2())
                .append(schoolRequirement3, other.getSchoolRequirement3())
                .append(subSchoolRequirement1, other.getSubSchoolRequirement1())
                .append(subSchoolRequirement2, other.getSubSchoolRequirement2())
                .append(subSchoolRequirement3, other.getSubSchoolRequirement3()).build();
    }
}