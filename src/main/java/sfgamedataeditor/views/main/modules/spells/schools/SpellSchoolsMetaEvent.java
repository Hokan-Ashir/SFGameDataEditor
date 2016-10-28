package sfgamedataeditor.views.main.modules.spells.schools;

import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.EventCreator;
import sfgamedataeditor.events.types.AbstractViewableMetaEvent;
import sfgamedataeditor.events.types.ClearViewEvent;
import sfgamedataeditor.events.types.SetModuleNameEvent;
import sfgamedataeditor.events.types.ShowViewEvent;
import sfgamedataeditor.views.main.MainView;
import sfgamedataeditor.views.main.modules.common.ModulesMetaEvent;
import sfgamedataeditor.views.main.modules.common.modules.ModulesView;

public class SpellSchoolsMetaEvent extends AbstractViewableMetaEvent<ModulesMetaEvent> {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addEvents() {
        SetModuleNameEvent moduleNameEvent = new SetModuleNameEvent(ModulesView.class, MainView.class);
        ShowSpellSchoolsViewEvent spellSchoolsViewEvent = EventCreator.createEvent(SpellSchoolsView.class, MainView.class, ShowSpellSchoolsViewEvent.class);
        ClearViewEvent clearViewEvent = new ClearViewEvent<>(SpellSchoolsView.class, MainView.class);
        addEvents(moduleNameEvent, spellSchoolsViewEvent, clearViewEvent);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ModulesMetaEvent createParentMetaEvent() {
        return new ModulesMetaEvent();
    }
}

class ShowSpellSchoolsViewEvent extends ShowViewEvent<SpellSchoolsView, MainView, Object> {

    public ShowSpellSchoolsViewEvent(ClassTuple<SpellSchoolsView, MainView> tuple) {
        super(tuple);
    }
}
