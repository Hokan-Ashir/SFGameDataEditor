package sfgamedataeditor.views.main.modules.items.weapons;

import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.EventCreator;
import sfgamedataeditor.events.types.AbstractMetaEvent;
import sfgamedataeditor.events.types.ClearViewEvent;
import sfgamedataeditor.events.types.ShowViewEvent;
import sfgamedataeditor.views.main.modules.items.ItemTypesView;

public class WeaponTypesMetaEvent extends AbstractMetaEvent {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addEvents() {
        ShowWeaponTypesListViewEvent event = EventCreator.createEvent(WeaponsTypesListView.class, ItemTypesView.class, ShowWeaponTypesListViewEvent.class);
        ClearViewEvent clearViewEvent = new ClearViewEvent(WeaponsTypesListView.class, ItemTypesView.class);
        addEvents(event, clearViewEvent);
    }
}

class ShowWeaponTypesListViewEvent extends ShowViewEvent<WeaponsTypesListView, ItemTypesView, Object> {

    public ShowWeaponTypesListViewEvent(ClassTuple<WeaponsTypesListView, ItemTypesView> tuple) {
        super(tuple);
    }
}
