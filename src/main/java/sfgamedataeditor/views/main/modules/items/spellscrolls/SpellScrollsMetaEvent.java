package sfgamedataeditor.views.main.modules.items.spellscrolls;

import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.EventCreator;
import sfgamedataeditor.events.types.AbstractMetaEvent;
import sfgamedataeditor.events.types.ClearViewEvent;
import sfgamedataeditor.events.types.ShowViewEvent;
import sfgamedataeditor.views.main.modules.items.ItemTypesView;

public class SpellScrollsMetaEvent extends AbstractMetaEvent {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addEvents() {
        ShowSpellScrollsViewEvent event = EventCreator.createEvent(SpellScrollsListView.class, ItemTypesView.class, ShowSpellScrollsViewEvent.class);
        ClearViewEvent clearViewEvent = new ClearViewEvent(SpellScrollsListView.class, ItemTypesView.class);
        addEvents(event, clearViewEvent);
    }
}

class ShowSpellScrollsViewEvent extends ShowViewEvent<SpellScrollsListView, ItemTypesView, Object> {

    public ShowSpellScrollsViewEvent(ClassTuple<SpellScrollsListView, ItemTypesView> tuple) {
        super(tuple);
    }
}