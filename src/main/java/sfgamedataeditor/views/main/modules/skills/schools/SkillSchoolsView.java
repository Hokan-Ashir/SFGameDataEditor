package sfgamedataeditor.views.main.modules.skills.schools;

import sfgamedataeditor.datamapping.Mappings;
import sfgamedataeditor.events.processing.ViewRegister;
import sfgamedataeditor.mvc.objects.AbstractController;
import sfgamedataeditor.mvc.objects.Model;
import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.common.AbstractModulesView;
import sfgamedataeditor.views.main.MainView;
import sfgamedataeditor.views.main.modules.skills.schools.parameters.SkillParameterView;

public class SkillSchoolsView extends AbstractModulesView {

    public SkillSchoolsView() {
        super(I18N.INSTANCE.getMessage("skillSchools"));
    }

    @Override
    protected Model createModel() {
        return null;
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
    public void render() {
        MainView mainView = ViewRegister.INSTANCE.getView(MainView.class);
        mainView.renderViewInsideNavigationPanel(this);
    }

    @Override
    public void unrender() {

    }

    @Override
    public Class<? extends AbstractController> getControllerClass() {
        return null;
    }

//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    protected void setEventParameter(AbstractMetaEvent metaEvent) {
//        super.setEventParameter(metaEvent);
//        parameter.setSkillSchoolId(getSelectedSchoolId());
//
//        LevelableView<SkillSchoolsView> levelableView = ViewRegister.INSTANCE.getView(new ClassTuple(LevelableView.class, SkillSchoolsView.class));
//        if (levelableView != null) {
//            parameter.setSkillLevel(levelableView.getSelectedLevel());
//        } else {
//            parameter.setSkillLevel(1);
//        }
//
//        metaEvent.setEventParameter(ShowSkillParameterViewEvent.class, parameter);
//    }
//
//    private int getSelectedSchoolId() {
//        String selectedSkillSchool = getSelectedModuleValue();
//        return Mappings.INSTANCE.SKILL_SCHOOL_MAP.get(selectedSkillSchool);
//    }
}
