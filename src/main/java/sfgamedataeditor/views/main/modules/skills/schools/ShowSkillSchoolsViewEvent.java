package sfgamedataeditor.views.main.modules.skills.schools;

import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.ShowViewEvent;
import sfgamedataeditor.views.main.modules.ModulesView;

public class ShowSkillSchoolsViewEvent extends ShowViewEvent<SkillSchoolsView, ModulesView, Object> {

    public ShowSkillSchoolsViewEvent(ClassTuple<SkillSchoolsView, ModulesView> tuple) {
        super(tuple);
    }
}
