package sfgamedataeditor.views.main.modules.spells.schools.spells.parameters;

import sfgamedataeditor.events.EventCreator;
import sfgamedataeditor.events.types.AbstractMetaEvent;
import sfgamedataeditor.views.common.levelable.LevelableView;
import sfgamedataeditor.views.common.levelable.ShowLevelableViewEvent;
import sfgamedataeditor.views.main.modules.spells.schools.spells.SpellsView;

public class SpellParameterViewMetaEvent extends AbstractMetaEvent {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addEvents() {
        ShowLevelableViewEvent levelableViewEvent = EventCreator.createEvent(LevelableView.class, SpellsView.class, ShowLevelableViewEvent.class);
        ShowSpellParameterViewEvent event = EventCreator.createEvent(SpellParameterView.class, SpellsView.class, ShowSpellParameterViewEvent.class);
        addEvents(levelableViewEvent, event);
    }
}
