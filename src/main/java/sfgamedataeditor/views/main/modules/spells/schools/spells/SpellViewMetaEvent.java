package sfgamedataeditor.views.main.modules.spells.schools.spells;

import sfgamedataeditor.events.EventCreator;
import sfgamedataeditor.events.types.AbstractViewableMetaEvent;
import sfgamedataeditor.events.types.ClearViewEvent;
import sfgamedataeditor.events.types.SetModuleNameEvent;
import sfgamedataeditor.views.main.MainView;
import sfgamedataeditor.views.main.modules.spells.schools.SpellSchoolsMetaEvent;
import sfgamedataeditor.views.main.modules.spells.schools.SpellSchoolsView;

public class SpellViewMetaEvent extends AbstractViewableMetaEvent<SpellSchoolsMetaEvent> {

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

    /**
     * {@inheritDoc}
     */
    @Override
    public SpellSchoolsMetaEvent createParentMetaEvent() {
        return new SpellSchoolsMetaEvent();
    }
}

