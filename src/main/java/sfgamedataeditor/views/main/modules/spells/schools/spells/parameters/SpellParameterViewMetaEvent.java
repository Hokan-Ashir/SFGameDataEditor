package sfgamedataeditor.views.main.modules.spells.schools.spells.parameters;

import sfgamedataeditor.events.EventCreator;
import sfgamedataeditor.events.types.AbstractViewableMetaEvent;
import sfgamedataeditor.events.types.SetModuleNameEvent;
import sfgamedataeditor.views.common.levelable.LevelableView;
import sfgamedataeditor.views.common.levelable.ShowLevelableViewEvent;
import sfgamedataeditor.views.main.MainView;
import sfgamedataeditor.views.main.modules.spells.schools.spells.SpellViewMetaEvent;
import sfgamedataeditor.views.main.modules.spells.schools.spells.SpellsView;

public class SpellParameterViewMetaEvent extends AbstractViewableMetaEvent<SpellViewMetaEvent> {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addEvents() {
        SetModuleNameEvent moduleNameEvent = new SetModuleNameEvent(SpellsView.class, MainView.class);
        ShowLevelableViewEvent levelableViewEvent = EventCreator.createEvent(LevelableView.class, MainView.class, ShowLevelableViewEvent.class);
        ShowSpellParameterViewEvent event = EventCreator.createEvent(SpellParameterView.class, MainView.class, ShowSpellParameterViewEvent.class);
        addEvents(moduleNameEvent, levelableViewEvent, event);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SpellViewMetaEvent createParentMetaEvent() {
        return new SpellViewMetaEvent();
    }
}
