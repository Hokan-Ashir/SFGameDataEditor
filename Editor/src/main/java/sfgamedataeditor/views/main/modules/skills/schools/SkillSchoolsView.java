package sfgamedataeditor.views.main.modules.skills.schools;

import sfgamedataeditor.datamapping.Mappings;
import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.views.common.AbstractModulesView;
import sfgamedataeditor.views.main.modules.skills.schools.parameters.SkillParameterView;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

public class SkillSchoolsView extends AbstractModulesView {

    public SkillSchoolsView() {
        super(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "skillSchools"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fillSubViewsMappings() {
        addMappings(Mappings.INSTANCE.SKILL_SCHOOL_MAP.keySet(), SkillParameterView.class);
    }

    @Override
    public Class<? extends AbstractPresenter> getPresenterClass() {
        return SkillSchoolsPresenter.class;
    }
}