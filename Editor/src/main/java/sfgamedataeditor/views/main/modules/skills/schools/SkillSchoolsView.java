package sfgamedataeditor.views.main.modules.skills.schools;

import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.views.common.views.AbstractModulesView;
import sfgamedataeditor.views.main.modules.skills.schools.parameters.SkillParameterView;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeSet;

public class SkillSchoolsView extends AbstractModulesView {

    public SkillSchoolsView() {
        super(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "skillSchools"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fillSubViewsMappings() {
        Set<String> mappings = new TreeSet<>();
        ResourceBundle bundle = I18NService.INSTANCE.getBundle(I18NTypes.SKILL_SCHOOL_MAPPING);
        for (String key : bundle.keySet()) {
            if (key.equals("other")) {
                continue;
            }

            mappings.add(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, key));
        }

        addMappings(mappings, SkillParameterView.class);
    }

    @Override
    public Class<? extends AbstractPresenter> getPresenterClass() {
        return SkillSchoolsPresenter.class;
    }
}
