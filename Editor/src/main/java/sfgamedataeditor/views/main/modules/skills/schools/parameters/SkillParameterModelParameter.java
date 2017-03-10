package sfgamedataeditor.views.main.modules.skills.schools.parameters;

import sfgamedataeditor.mvc.objects.LevelableParameter;

import javax.swing.*;

public class SkillParameterModelParameter extends LevelableParameter {
    private final int skillSchoolId;

    public SkillParameterModelParameter(int skillSchoolId, int skillLevel, Icon icon) {
        super(skillLevel, icon);
        this.skillSchoolId = skillSchoolId;
    }

    public int getSkillSchoolId() {
        return skillSchoolId;
    }
}
