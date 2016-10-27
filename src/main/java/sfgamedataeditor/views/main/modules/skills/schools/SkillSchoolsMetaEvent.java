package sfgamedataeditor.views.main.modules.skills.schools;

import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.EventCreator;
import sfgamedataeditor.events.types.AbstractMetaEvent;
import sfgamedataeditor.events.types.ClearViewEvent;
import sfgamedataeditor.events.types.SetModuleNameEvent;
import sfgamedataeditor.events.types.ShowViewEvent;
import sfgamedataeditor.views.main.MainView;
import sfgamedataeditor.views.main.modules.common.modules.ModulesView;

public class SkillSchoolsMetaEvent extends AbstractMetaEvent {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addEvents() {
        SetModuleNameEvent setModuleNameEvent = new SetModuleNameEvent(ModulesView.class, MainView.class);
        ShowSkillSchoolsViewEvent skillSchoolsViewEvent = EventCreator.createEvent(SkillSchoolsView.class, MainView.class, ShowSkillSchoolsViewEvent.class);
        ClearViewEvent clearViewEvent = new ClearViewEvent(SkillSchoolsView.class, MainView.class);
        addEvents(setModuleNameEvent, skillSchoolsViewEvent, clearViewEvent);
    }
}

class ShowSkillSchoolsViewEvent extends ShowViewEvent<SkillSchoolsView, MainView, Object> {

    public ShowSkillSchoolsViewEvent(ClassTuple<SkillSchoolsView, MainView> tuple) {
        super(tuple);
    }
}
