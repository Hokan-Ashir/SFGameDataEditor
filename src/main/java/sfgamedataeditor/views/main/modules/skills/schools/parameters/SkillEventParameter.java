package sfgamedataeditor.views.main.modules.skills.schools.parameters;

public class SkillEventParameter {

    private int skillSchoolId;
    private int skillLevel;

    public SkillEventParameter(int skillSchoolId, int skillLevel) {
        this.skillSchoolId = skillSchoolId;
        this.skillLevel = skillLevel;
    }

    public int getSkillSchoolId() {
        return skillSchoolId;
    }

    public void setSkillSchoolId(int skillSchoolId) {
        this.skillSchoolId = skillSchoolId;
    }

    public int getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(int skillLevel) {
        this.skillLevel = skillLevel;
    }
}
