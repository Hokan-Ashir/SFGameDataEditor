package sfgamedataeditor.views.main.modules.spells.schools.spells;

import sfgamedataeditor.events.AbstractMetaEvent;
import sfgamedataeditor.events.EventCreator;
import sfgamedataeditor.views.main.modules.spells.schools.SpellSchoolsView;

public class SpellViewMetaEvent extends AbstractMetaEvent {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addEvents() {
        ShowSpellsViewEvent event = EventCreator.createEvent(SpellsView.class, SpellSchoolsView.class, ShowSpellsViewEvent.class);
        addEvent(event);
    }
}

