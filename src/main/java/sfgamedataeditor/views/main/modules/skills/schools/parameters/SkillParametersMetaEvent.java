package sfgamedataeditor.views.main.modules.skills.schools.parameters;

import sfgamedataeditor.events.EventCreator;
import sfgamedataeditor.events.types.AbstractMetaEvent;
import sfgamedataeditor.events.types.SetModuleNameEvent;
import sfgamedataeditor.views.common.levelable.LevelableView;
import sfgamedataeditor.views.common.levelable.ShowLevelableViewEvent;
import sfgamedataeditor.views.main.MainView;
import sfgamedataeditor.views.main.modules.skills.schools.SkillSchoolsView;

public class SkillParametersMetaEvent extends AbstractMetaEvent {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addEvents() {
        SetModuleNameEvent moduleNameEvent = new SetModuleNameEvent(SkillSchoolsView.class, MainView.class);
        ShowLevelableViewEvent levelableViewEvent = EventCreator.createEvent(LevelableView.class, MainView.class, ShowLevelableViewEvent.class);
        ShowSkillParameterViewEvent skillParameterViewEvent = EventCreator.createEvent(SkillParameterView.class, MainView.class, ShowSkillParameterViewEvent.class);
        addEvents(moduleNameEvent, levelableViewEvent, skillParameterViewEvent);
    }
}
