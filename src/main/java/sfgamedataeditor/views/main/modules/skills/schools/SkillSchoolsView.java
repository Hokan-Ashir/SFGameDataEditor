package sfgamedataeditor.views.main.modules.skills.schools;

import sfgamedataeditor.ViewRegister;
import sfgamedataeditor.datamapping.Mappings;
import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.EventHandlerRegister;
import sfgamedataeditor.events.ShowViewEvent;
import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.common.AbstractModulesView;
import sfgamedataeditor.views.common.LevelableView;
import sfgamedataeditor.views.common.ShowLevelableViewEvent;
import sfgamedataeditor.views.main.modules.common.modules.ModulesView;
import sfgamedataeditor.views.main.modules.skills.schools.parameters.ShowSkillParameterViewEvent;
import sfgamedataeditor.views.main.modules.skills.schools.parameters.SkillEventParameter;
import sfgamedataeditor.views.main.modules.skills.schools.parameters.SkillParameterView;

import java.util.ArrayList;
import java.util.List;

public class SkillSchoolsView extends AbstractModulesView<ModulesView> {

    private final SkillEventParameter parameter;

    public SkillSchoolsView(ModulesView parentView) {
        super(parentView, I18N.INSTANCE.getMessage("skillSchools"));
        parameter = new SkillEventParameter();
        EventHandlerRegister.INSTANCE.addEventHandler(new SkillEventHandler());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fillComboBoxMapping() {
        ClassTuple tuple1 = new ClassTuple(LevelableView.class, this);
        ShowLevelableViewEvent levelableViewEvent = new ShowLevelableViewEvent(tuple1);
        List<ShowViewEvent> events = new ArrayList<>();

        ClassTuple tuple = new ClassTuple<>(SkillParameterView.class, this);
        ShowSkillParameterViewEvent skillParameterViewEvent = new ShowSkillParameterViewEvent(tuple);

        events.add(levelableViewEvent);
        events.add(skillParameterViewEvent);
        for (String s : Mappings.INSTANCE.SKILL_SCHOOL_MAP.keySet()) {
            addMapping(s, events);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setEventParameter(ShowViewEvent event) {
        // TODO get rid of instance of
        if (event instanceof ShowSkillParameterViewEvent) {
            parameter.setSkillSchoolId(getSelectedSchoolId());

            LevelableView<SkillSchoolsView> levelableView = (LevelableView<SkillSchoolsView>) ViewRegister.INSTANCE.getView(new ClassTuple(LevelableView.class, this));
            if (levelableView != null) {
                parameter.setSkillLevel(levelableView.getSelectedLevel());
            }

            event.setObjectParameter(parameter);
        }
    }

    private int getSelectedSchoolId() {
        String selectedSkillSchool = (String) getSelectedModuleValue();
        return Mappings.INSTANCE.SKILL_SCHOOL_MAP.get(selectedSkillSchool);
    }
}
