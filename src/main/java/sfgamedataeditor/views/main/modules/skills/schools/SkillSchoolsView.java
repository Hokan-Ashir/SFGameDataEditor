package sfgamedataeditor.views.main.modules.skills.schools;

import sfgamedataeditor.datamapping.Mappings;
import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.EventHandlerRegister;
import sfgamedataeditor.events.processing.ViewRegister;
import sfgamedataeditor.events.types.AbstractMetaEvent;
import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.common.AbstractModulesView;
import sfgamedataeditor.views.common.AbstractView;
import sfgamedataeditor.views.common.levelable.LevelableView;
import sfgamedataeditor.views.main.MainView;
import sfgamedataeditor.views.main.modules.common.modules.ModulesView;
import sfgamedataeditor.views.main.modules.skills.schools.parameters.ShowSkillParameterViewEvent;
import sfgamedataeditor.views.main.modules.skills.schools.parameters.SkillEventParameter;
import sfgamedataeditor.views.main.modules.skills.schools.parameters.SkillParametersMetaEvent;

public class SkillSchoolsView extends AbstractModulesView<MainView, SkillSchoolsMetaEvent> {

    private final SkillEventParameter parameter;

    public SkillSchoolsView(MainView parentView) {
        super(parentView, I18N.INSTANCE.getMessage("skillSchools"));
        parameter = new SkillEventParameter();
        EventHandlerRegister.INSTANCE.addEventHandler(new SkillEventHandler());
//        getSubModulesPanel().setLayout(new BoxLayout(getSubModulesPanel(), BoxLayout.Y_AXIS));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fillComboBoxMapping() {
        for (String s : Mappings.INSTANCE.SKILL_SCHOOL_MAP.keySet()) {
            addMapping(s, new SkillParametersMetaEvent());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setEventParameter(AbstractMetaEvent metaEvent) {
        super.setEventParameter(metaEvent);
        parameter.setSkillSchoolId(getSelectedSchoolId());

        LevelableView<SkillSchoolsView> levelableView = (LevelableView<SkillSchoolsView>) ViewRegister.INSTANCE.getView(new ClassTuple(LevelableView.class, SkillSchoolsView.class));
        if (levelableView != null) {
            parameter.setSkillLevel(levelableView.getSelectedLevel());
        } else {
            parameter.setSkillLevel(1);
        }

        metaEvent.setEventParameter(ShowSkillParameterViewEvent.class, parameter);
    }

    private int getSelectedSchoolId() {
        String selectedSkillSchool = getSelectedModuleValue();
        return Mappings.INSTANCE.SKILL_SCHOOL_MAP.get(selectedSkillSchool);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<SkillSchoolsMetaEvent> getMetaEventClass() {
        return SkillSchoolsMetaEvent.class;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<? extends AbstractView> getParentHierarchyClass() {
        return ModulesView.class;
    }
}
