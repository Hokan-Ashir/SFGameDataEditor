package sfgamedataeditor.views.main.modules.spells.schools.spells;

import sfgamedataeditor.events.EventCreator;
import sfgamedataeditor.events.types.AbstractMetaEvent;
import sfgamedataeditor.events.types.ClearViewEvent;
import sfgamedataeditor.events.types.SetModuleNameEvent;
import sfgamedataeditor.views.main.MainView;
import sfgamedataeditor.views.main.modules.spells.schools.SpellSchoolsView;

public class SpellViewMetaEvent extends AbstractMetaEvent {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addEvents() {
        SetModuleNameEvent moduleNameEvent = new SetModuleNameEvent(SpellSchoolsView.class, MainView.class);
        ShowSpellsViewEvent showSpellsViewEvent = EventCreator.createEvent(SpellsView.class, MainView.class, ShowSpellsViewEvent.class);
        ClearViewEvent clearViewEvent = new ClearViewEvent<>(SpellsView.class, MainView.class);
        addEvents(moduleNameEvent, showSpellsViewEvent, clearViewEvent);
    }
}

