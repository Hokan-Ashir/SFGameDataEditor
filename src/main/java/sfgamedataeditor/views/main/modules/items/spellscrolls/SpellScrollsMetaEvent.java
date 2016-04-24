package sfgamedataeditor.views.main.modules.items.spellscrolls;

import sfgamedataeditor.events.AbstractMetaEvent;
import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.EventCreator;
import sfgamedataeditor.events.ShowViewEvent;
import sfgamedataeditor.views.main.modules.items.ItemTypesView;

public class SpellScrollsMetaEvent extends AbstractMetaEvent {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addEvents() {
        ShowSpellScrollsViewEvent event = EventCreator.createEvent(SpellScrollsListView.class, ItemTypesView.class, ShowSpellScrollsViewEvent.class);
        addEvent(event);
    }
}

class ShowSpellScrollsViewEvent extends ShowViewEvent<SpellScrollsListView, ItemTypesView, Object> {

    public ShowSpellScrollsViewEvent(ClassTuple<SpellScrollsListView, ItemTypesView> tuple) {
        super(tuple);
    }
}