package sfgamedataeditor.views.main.modules.skills.schools;

import sfgamedataeditor.datamapping.Mappings;
import sfgamedataeditor.views.common.AbstractModulesPresenter;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.main.modules.skills.schools.parameters.SkillParameterModel;
import sfgamedataeditor.views.main.modules.skills.schools.parameters.SkillParameterModelParameter;

public class SkillSchoolsPresenter extends AbstractModulesPresenter<ModuleParameter, SkillSchoolsView, SkillParameterModel> {
    public SkillSchoolsPresenter(SkillSchoolsView view) {
        super(view);
    }

    @Override
    protected SkillParameterModel createModel() {
        String selectedSkillSchool = getView().getSelectedModuleName();
        int skillSchoolId = Mappings.INSTANCE.SKILL_SCHOOL_MAP.get(selectedSkillSchool);
        SkillParameterModelParameter parameter = new SkillParameterModelParameter(skillSchoolId, 1);
        return new SkillParameterModel(parameter);
    }
}
