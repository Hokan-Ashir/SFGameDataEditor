package sfgamedataeditor.views.main.modules.spells.schools;

import sfgamedataeditor.events.AbstractMetaEvent;
import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.EventCreator;
import sfgamedataeditor.events.ShowViewEvent;
import sfgamedataeditor.views.main.modules.common.modules.ModulesView;

public class SpellSchoolsMetaEvent extends AbstractMetaEvent {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addEvents() {
        ShowSpellSchoolsViewEvent event = EventCreator.createEvent(SpellSchoolsView.class, ModulesView.class, ShowSpellSchoolsViewEvent.class);
        addEvent(event);
    }
}

class ShowSpellSchoolsViewEvent extends ShowViewEvent<SpellSchoolsView, ModulesView, Object> {

    public ShowSpellSchoolsViewEvent(ClassTuple<SpellSchoolsView, ModulesView> tuple) {
        super(tuple);
    }
}
