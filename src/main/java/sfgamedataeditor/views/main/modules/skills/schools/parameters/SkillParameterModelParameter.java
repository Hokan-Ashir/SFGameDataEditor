package sfgamedataeditor.views.main.modules.skills.schools.parameters;

public class SkillParameterModelParameter {
    private int skillSchoolId;
    private int skillLevel;

    public SkillParameterModelParameter(int skillSchoolId, int skillLevel) {
        this.skillSchoolId = skillSchoolId;
        this.skillLevel = skillLevel;
    }

    public int getSkillSchoolId() {
        return skillSchoolId;
    }

    public int getSkillLevel() {
        return skillLevel;
    }

    public void setSkillSchoolId(int skillSchoolId) {
        this.skillSchoolId = skillSchoolId;
    }

    public void setSkillLevel(int skillLevel) {
        this.skillLevel = skillLevel;
    }
}
