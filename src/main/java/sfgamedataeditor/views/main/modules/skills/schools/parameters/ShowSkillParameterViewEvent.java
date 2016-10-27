package sfgamedataeditor.views.main.modules.skills.schools.parameters;

import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.types.ShowViewEvent;
import sfgamedataeditor.views.main.MainView;

public class ShowSkillParameterViewEvent extends ShowViewEvent<SkillParameterView, MainView, SkillEventParameter> {

    public ShowSkillParameterViewEvent(ClassTuple<SkillParameterView, MainView> tuple) {
        super(tuple);
    }
}
