package sfgamedataeditor.views.skills;

import sfgamedataeditor.datamapping.Mappings;
import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.ModulesView;
import sfgamedataeditor.views.common.AbstractModulesView;

public class SkillSchoolsView extends AbstractModulesView<ModulesView> {


    public SkillSchoolsView(ModulesView parentView) {
        super(parentView);
        setModulesLabelText(I18N.getMessage("skillSchools"));
    }

    @Override
    protected void fillComboBoxMapping() {
        for (String s : Mappings.INSTANCE.SKILL_SCHOOL_MAP.keySet()) {
            getComboBoxMapping().put(s, SkillParameterView.class);
        }
    }

    @Override
    public void show() {
        getParentView().getSubModulesPanel().removeAll();
        getParentView().getSubModulesPanel().add(getMainPanel());
        getParentView().getSubModulesPanel().revalidate();
        getParentView().getSubModulesPanel().repaint();
    }
}
