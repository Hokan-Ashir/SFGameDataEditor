package sfgamedataeditor.views.main.modules.items.runes;

import sfgamedataeditor.events.AbstractMetaEvent;
import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.EventCreator;
import sfgamedataeditor.events.ShowViewEvent;
import sfgamedataeditor.views.main.modules.items.ItemTypesView;

public class RuneRacesMetaEvent extends AbstractMetaEvent {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addEvents() {
        ShowRuneRacesViewEvent event = EventCreator.createEvent(RuneRacesListView.class, ItemTypesView.class, ShowRuneRacesViewEvent.class);
        addEvent(event);
    }
}

class ShowRuneRacesViewEvent extends ShowViewEvent<RuneRacesListView, ItemTypesView, Object> {

    public ShowRuneRacesViewEvent(ClassTuple<RuneRacesListView, ItemTypesView> tuple) {
        super(tuple);
    }
}
