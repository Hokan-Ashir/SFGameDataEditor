package sfgamedataeditor.views.main.modules.skills.schools.parameters;

import sfgamedataeditor.mvc.ModelCreator;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.common.ModulesModel;
import sfgamedataeditor.views.utility.ViewTools;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

public class SkillSchoolsFromSkillParameterModelCreator implements ModelCreator<ModulesModel, SkillParameterModel> {
    @Override
    public ModulesModel createModel(SkillParameterModel childModel) {
        int skillSchoolId = childModel.getParameter().getSkillSchoolId();
        String skillSchoolKey = ViewTools.getKeyStringByPropertyValue(String.valueOf(skillSchoolId), I18NTypes.SKILL_SCHOOL_MAPPING);
        String skillSchoolName = I18NService.INSTANCE.getMessage(I18NTypes.COMMON, skillSchoolKey);
        ModuleParameter parameter = new ModuleParameter(skillSchoolName);
        return new ModulesModel(parameter);
    }
}
