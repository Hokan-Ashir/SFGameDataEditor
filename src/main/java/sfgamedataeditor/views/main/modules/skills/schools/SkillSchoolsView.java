package sfgamedataeditor.views.main.modules.skills.schools;

import sfgamedataeditor.datamapping.Mappings;
import sfgamedataeditor.mvc.objects.AbstractController;
import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.common.AbstractModulesView;
import sfgamedataeditor.views.main.modules.skills.schools.parameters.SkillParameterView;

public class SkillSchoolsView extends AbstractModulesView {

    public SkillSchoolsView() {
        super(I18N.INSTANCE.getMessage("skillSchools"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fillComboBoxMapping() {
        for (String s : Mappings.INSTANCE.SKILL_SCHOOL_MAP.keySet()) {
            addMapping(s, SkillParameterView.class);
        }
    }

    @Override
    public Class<? extends AbstractController> getControllerClass() {
        return SkillSchoolsController.class;
    }
}
