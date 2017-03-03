package sfgamedataeditor.views.main.modules.skills.schools.parameters;

import sfgamedataeditor.datamapping.Mappings;
import sfgamedataeditor.mvc.ModelCreator;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.common.ModulesModel;

import java.util.Map;

public class SkillSchoolsFromSkillParameterModelCreator implements ModelCreator<ModulesModel, SkillParameterModel> {
    @Override
    public ModulesModel createModel(SkillParameterModel childModel) {
        int skillSchoolId = childModel.getParameter().getSkillSchoolId();
        String skillSchoolName = null;
        for (Map.Entry<String, Integer> skillSchoolObject : Mappings.INSTANCE.SKILL_SCHOOL_MAP.entrySet()) {
            if (skillSchoolObject.getValue().equals(skillSchoolId)) {
                skillSchoolName = skillSchoolObject.getKey();
                break;
            }
        }

        ModuleParameter parameter = new ModuleParameter(skillSchoolName);
        return new ModulesModel(parameter);
    }
}
