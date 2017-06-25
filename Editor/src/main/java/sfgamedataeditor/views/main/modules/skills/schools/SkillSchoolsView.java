package sfgamedataeditor.views.main.modules.skills.schools;

import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.views.common.SubViewPanelTuple;
import sfgamedataeditor.views.common.views.AbstractModulesView;
import sfgamedataeditor.views.main.modules.skills.schools.parameters.SkillParameterView;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SkillSchoolsView extends AbstractModulesView {

    public SkillSchoolsView() {
        super(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "skillSchools"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fillSubViewsMappings() {
        List<SubViewPanelTuple> mappings = new ArrayList<>();
        ResourceBundle bundle = I18NService.INSTANCE.getBundle(I18NTypes.SKILL_SCHOOL_MAPPING);
        for (String key : bundle.keySet()) {
            if (key.equals("other")) {
                continue;
            }

            mappings.add(new SubViewPanelTuple(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, key)));
        }

        addMappings(mappings, SkillParameterView.class);
    }

    @Override
    public Class<? extends AbstractPresenter> getPresenterClass() {
        return SkillSchoolsPresenter.class;
    }
}
