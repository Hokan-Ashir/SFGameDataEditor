package sfgamedataeditor.views.main.modules.skills.schools.parameters;

import sfgamedataeditor.events.EventCreator;
import sfgamedataeditor.events.types.AbstractMetaEvent;
import sfgamedataeditor.views.common.levelable.LevelableView;
import sfgamedataeditor.views.common.levelable.ShowLevelableViewEvent;
import sfgamedataeditor.views.main.modules.skills.schools.SkillSchoolsView;

public class SkillParametersMetaEvent extends AbstractMetaEvent {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addEvents() {
        ShowLevelableViewEvent levelableViewEvent = EventCreator.createEvent(LevelableView.class, SkillSchoolsView.class, ShowLevelableViewEvent.class);
        ShowSkillParameterViewEvent skillParameterViewEvent = EventCreator.createEvent(SkillParameterView.class, SkillSchoolsView.class, ShowSkillParameterViewEvent.class);
        addEvents(levelableViewEvent, skillParameterViewEvent);
    }
}
