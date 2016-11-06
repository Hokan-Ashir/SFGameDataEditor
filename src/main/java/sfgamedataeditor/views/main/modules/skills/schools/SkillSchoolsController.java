package sfgamedataeditor.views.main.modules.skills.schools;

import sfgamedataeditor.datamapping.Mappings;
import sfgamedataeditor.mvc.objects.Model;
import sfgamedataeditor.views.common.AbstractModulesController;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.main.modules.skills.schools.parameters.SkillParameterModel;
import sfgamedataeditor.views.main.modules.skills.schools.parameters.SkillParameterModelParameter;

public class SkillSchoolsController extends AbstractModulesController<ModuleParameter, SkillSchoolsView> {
    public SkillSchoolsController(SkillSchoolsView view) {
        super(view);
    }

    @Override
    public void updateView() {
        if (getModel() == null) {
            setModulesComboBoxValue(null);
            return;
        }

        String moduleName = getModel().getParameter().getModuleName();
        if (isElementExistsInComboBox(moduleName)) {
            setModulesComboBoxValue(moduleName);
        } else {
            setModulesComboBoxValue(null);
        }
    }

    @Override
    public <T extends Model<?>> T createModel() {
        String selectedSkillSchool = getView().getSelectedModuleValue();
        int skillSchoolId = Mappings.INSTANCE.SKILL_SCHOOL_MAP.get(selectedSkillSchool);
        SkillParameterModelParameter parameter = new SkillParameterModelParameter(skillSchoolId, 1);
        return (T) new SkillParameterModel(parameter);
    }
}
