package sfgamedataeditor.views.main.modules.items.runes;

import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.EventCreator;
import sfgamedataeditor.events.types.AbstractMetaEvent;
import sfgamedataeditor.events.types.ClearViewEvent;
import sfgamedataeditor.events.types.ShowViewEvent;
import sfgamedataeditor.views.main.modules.items.ItemTypesView;

public class RuneRacesMetaEvent extends AbstractMetaEvent {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addEvents() {
        ShowRuneRacesViewEvent event = EventCreator.createEvent(RuneRacesListView.class, ItemTypesView.class, ShowRuneRacesViewEvent.class);
        ClearViewEvent clearViewEvent = new ClearViewEvent(RuneRacesListView.class, ItemTypesView.class);
        addEvents(event, clearViewEvent);
    }
}

class ShowRuneRacesViewEvent extends ShowViewEvent<RuneRacesListView, ItemTypesView, Object> {

    public ShowRuneRacesViewEvent(ClassTuple<RuneRacesListView, ItemTypesView> tuple) {
        super(tuple);
    }
}
