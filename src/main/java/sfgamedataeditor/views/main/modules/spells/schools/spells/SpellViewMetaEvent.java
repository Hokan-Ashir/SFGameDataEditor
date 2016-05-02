package sfgamedataeditor.views.main.modules.spells.schools.spells;

import sfgamedataeditor.events.EventCreator;
import sfgamedataeditor.events.types.AbstractMetaEvent;
import sfgamedataeditor.events.types.ClearViewEvent;
import sfgamedataeditor.views.main.modules.spells.schools.SpellSchoolsView;

public class SpellViewMetaEvent extends AbstractMetaEvent {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addEvents() {
        ShowSpellsViewEvent event = EventCreator.createEvent(SpellsView.class, SpellSchoolsView.class, ShowSpellsViewEvent.class);
        ClearViewEvent clearViewEvent = new ClearViewEvent(SpellsView.class, SpellSchoolsView.class);
        addEvents(event, clearViewEvent);
    }
}

