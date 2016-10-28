package sfgamedataeditor.views.main.modules.items.runes;

import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.EventCreator;
import sfgamedataeditor.events.types.AbstractViewableMetaEvent;
import sfgamedataeditor.events.types.ClearViewEvent;
import sfgamedataeditor.events.types.SetModuleNameEvent;
import sfgamedataeditor.events.types.ShowViewEvent;
import sfgamedataeditor.views.main.MainView;
import sfgamedataeditor.views.main.modules.items.ItemTypesMetaEvent;
import sfgamedataeditor.views.main.modules.items.ItemTypesView;

public class RuneRacesMetaEvent extends AbstractViewableMetaEvent<ItemTypesMetaEvent> {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addEvents() {
        SetModuleNameEvent moduleNameEvent = new SetModuleNameEvent(ItemTypesView.class, MainView.class);
        ShowRuneRacesViewEvent event = EventCreator.createEvent(RuneRacesListView.class, MainView.class, ShowRuneRacesViewEvent.class);
        ClearViewEvent clearViewEvent = new ClearViewEvent<>(RuneRacesListView.class, MainView.class);
        addEvents(moduleNameEvent, event, clearViewEvent);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ItemTypesMetaEvent createParentMetaEvent() {
        return new ItemTypesMetaEvent();
    }
}

class ShowRuneRacesViewEvent extends ShowViewEvent<RuneRacesListView, MainView, Object> {

    public ShowRuneRacesViewEvent(ClassTuple<RuneRacesListView, MainView> tuple) {
        super(tuple);
    }
}
