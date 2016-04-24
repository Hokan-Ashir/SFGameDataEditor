package sfgamedataeditor.views.main.modules.skills.schools;

import sfgamedataeditor.events.AbstractMetaEvent;
import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.EventCreator;
import sfgamedataeditor.events.ShowViewEvent;
import sfgamedataeditor.views.main.modules.common.modules.ModulesView;

public class SkillSchoolsMetaEvent extends AbstractMetaEvent {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addEvents() {
        ShowSkillSchoolsViewEvent event = EventCreator.createEvent(SkillSchoolsView.class, ModulesView.class, ShowSkillSchoolsViewEvent.class);
        addEvent(event);
    }
}

class ShowSkillSchoolsViewEvent extends ShowViewEvent<SkillSchoolsView, ModulesView, Object> {

    public ShowSkillSchoolsViewEvent(ClassTuple<SkillSchoolsView, ModulesView> tuple) {
        super(tuple);
    }
}
