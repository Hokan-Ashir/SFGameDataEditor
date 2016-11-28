package sfgamedataeditor.views.main.modules.skills.schools.parameters;

public class SkillParameterModelParameter {
    private final int skillSchoolId;
    private int skillLevel;

    public SkillParameterModelParameter(int skillSchoolId, int skillLevel) {
        this.skillSchoolId = skillSchoolId;
        this.skillLevel = skillLevel;
    }

    public void setSkillLevel(int skillLevel) {
        this.skillLevel = skillLevel;
    }

    public int getSkillSchoolId() {
        return skillSchoolId;
    }

    public int getSkillLevel() {
        return skillLevel;
    }
}
