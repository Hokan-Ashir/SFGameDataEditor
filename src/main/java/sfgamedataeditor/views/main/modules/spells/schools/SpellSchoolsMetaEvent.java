package sfgamedataeditor.views.main.modules.spells.schools;

import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.EventCreator;
import sfgamedataeditor.events.types.AbstractMetaEvent;
import sfgamedataeditor.events.types.ClearViewEvent;
import sfgamedataeditor.events.types.ShowViewEvent;
import sfgamedataeditor.views.main.modules.common.modules.ModulesView;

public class SpellSchoolsMetaEvent extends AbstractMetaEvent {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addEvents() {
        ShowSpellSchoolsViewEvent event = EventCreator.createEvent(SpellSchoolsView.class, ModulesView.class, ShowSpellSchoolsViewEvent.class);
        ClearViewEvent clearViewEvent = new ClearViewEvent(SpellSchoolsView.class, ModulesView.class);
        addEvents(event, clearViewEvent);
    }
}

class ShowSpellSchoolsViewEvent extends ShowViewEvent<SpellSchoolsView, ModulesView, Object> {

    public ShowSpellSchoolsViewEvent(ClassTuple<SpellSchoolsView, ModulesView> tuple) {
        super(tuple);
    }
}
