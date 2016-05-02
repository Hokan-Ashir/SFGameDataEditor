package sfgamedataeditor.views.main.modules.skills.schools;

import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.EventCreator;
import sfgamedataeditor.events.types.AbstractMetaEvent;
import sfgamedataeditor.events.types.ClearViewEvent;
import sfgamedataeditor.events.types.ShowViewEvent;
import sfgamedataeditor.views.main.modules.common.modules.ModulesView;

public class SkillSchoolsMetaEvent extends AbstractMetaEvent {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addEvents() {
        ShowSkillSchoolsViewEvent event = EventCreator.createEvent(SkillSchoolsView.class, ModulesView.class, ShowSkillSchoolsViewEvent.class);
        ClearViewEvent clearViewEvent = new ClearViewEvent(SkillSchoolsView.class, ModulesView.class);
        addEvents(event, clearViewEvent);
    }
}

class ShowSkillSchoolsViewEvent extends ShowViewEvent<SkillSchoolsView, ModulesView, Object> {

    public ShowSkillSchoolsViewEvent(ClassTuple<SkillSchoolsView, ModulesView> tuple) {
        super(tuple);
    }
}
