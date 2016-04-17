package sfgamedataeditor.views.main.modules.items.runes;

import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.ShowViewEvent;
import sfgamedataeditor.views.main.modules.items.ItemTypesView;

public class ShowRuneRacesViewEvent extends ShowViewEvent<RuneRacesListView, ItemTypesView, Object> {

    public ShowRuneRacesViewEvent(ClassTuple<RuneRacesListView, ItemTypesView> tuple) {
        super(tuple);
    }
}
