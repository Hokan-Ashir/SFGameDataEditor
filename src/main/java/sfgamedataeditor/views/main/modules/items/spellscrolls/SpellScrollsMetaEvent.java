package sfgamedataeditor.views.main.modules.items.spellscrolls;

import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.EventCreator;
import sfgamedataeditor.events.types.AbstractMetaEvent;
import sfgamedataeditor.events.types.ClearViewEvent;
import sfgamedataeditor.events.types.SetModuleNameEvent;
import sfgamedataeditor.events.types.ShowViewEvent;
import sfgamedataeditor.views.main.MainView;
import sfgamedataeditor.views.main.modules.items.ItemTypesView;

public class SpellScrollsMetaEvent extends AbstractMetaEvent {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addEvents() {
        SetModuleNameEvent moduleNameEvent = new SetModuleNameEvent(ItemTypesView.class, MainView.class);
        ShowSpellScrollsViewEvent event = EventCreator.createEvent(SpellScrollsListView.class, MainView.class, ShowSpellScrollsViewEvent.class);
        ClearViewEvent clearViewEvent = new ClearViewEvent<>(SpellScrollsListView.class, MainView.class);
        addEvents(moduleNameEvent, event, clearViewEvent);
    }
}

class ShowSpellScrollsViewEvent extends ShowViewEvent<SpellScrollsListView, MainView, Object> {

    public ShowSpellScrollsViewEvent(ClassTuple<SpellScrollsListView, MainView> tuple) {
        super(tuple);
    }
}