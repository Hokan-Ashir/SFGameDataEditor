package sfgamedataeditor.views.main.modules.skills.schools;

import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.common.presenters.AbstractModulesPresenter;
import sfgamedataeditor.views.main.modules.skills.schools.parameters.SkillParameterModel;
import sfgamedataeditor.views.main.modules.skills.schools.parameters.SkillParameterModelParameter;
import sfgamedataeditor.views.utility.ViewTools;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

public class SkillSchoolsPresenter extends AbstractModulesPresenter<ModuleParameter, SkillSchoolsView, SkillParameterModel> {
    public SkillSchoolsPresenter(SkillSchoolsView view) {
        super(view);
    }

    @Override
    protected SkillParameterModel createModel() {
        String selectedSkillSchool = getView().getSelectedModuleName();
        String skillSchoolKey = ViewTools.getKeyStringByPropertyValue(selectedSkillSchool, I18NTypes.COMMON);
        int skillSchoolId = Integer.parseInt(I18NService.INSTANCE.getMessage(I18NTypes.SKILL_SCHOOL_MAPPING, skillSchoolKey));
        SkillParameterModelParameter parameter = new SkillParameterModelParameter(skillSchoolId, 1, null);
        return new SkillParameterModel(parameter);
    }
}
