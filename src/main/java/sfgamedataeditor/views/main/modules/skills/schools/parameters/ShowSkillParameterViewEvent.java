package sfgamedataeditor.views.main.modules.skills.schools.parameters;

import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.types.ShowViewEvent;
import sfgamedataeditor.views.main.modules.skills.schools.SkillSchoolsView;

public class ShowSkillParameterViewEvent extends ShowViewEvent<SkillParameterView, SkillSchoolsView, SkillEventParameter> {

    public ShowSkillParameterViewEvent(ClassTuple<SkillParameterView, SkillSchoolsView> tuple) {
        super(tuple);
    }
}
