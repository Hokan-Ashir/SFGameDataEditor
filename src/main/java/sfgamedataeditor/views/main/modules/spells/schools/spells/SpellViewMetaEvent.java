package sfgamedataeditor.views.main.modules.spells.schools.spells;

import sfgamedataeditor.events.EventCreator;
import sfgamedataeditor.events.types.AbstractMetaEvent;
import sfgamedataeditor.events.types.ClearViewEvent;
import sfgamedataeditor.events.types.SetModuleNameEvent;
import sfgamedataeditor.views.main.modules.common.modules.ModulesView;
import sfgamedataeditor.views.main.modules.spells.schools.SpellSchoolsView;

public class SpellViewMetaEvent extends AbstractMetaEvent {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addEvents() {
        SetModuleNameEvent moduleNameEvent = new SetModuleNameEvent(SpellSchoolsView.class, ModulesView.class);
        ShowSpellsViewEvent showSpellsViewEvent = EventCreator.createEvent(SpellsView.class, SpellSchoolsView.class, ShowSpellsViewEvent.class);
        ClearViewEvent clearViewEvent = new ClearViewEvent(SpellsView.class, SpellSchoolsView.class);
        addEvents(moduleNameEvent, showSpellsViewEvent, clearViewEvent);
    }
}

