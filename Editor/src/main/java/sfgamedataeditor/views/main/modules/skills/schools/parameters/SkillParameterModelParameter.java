package sfgamedataeditor.views.main.modules.skills.schools.parameters;

import sfgamedataeditor.mvc.objects.LevelableParameter;

public class SkillParameterModelParameter extends LevelableParameter {
    private final int skillSchoolId;

    public SkillParameterModelParameter(int skillSchoolId, int skillLevel) {
        super(skillLevel);
        this.skillSchoolId = skillSchoolId;
    }

    public int getSkillSchoolId() {
        return skillSchoolId;
    }
}
