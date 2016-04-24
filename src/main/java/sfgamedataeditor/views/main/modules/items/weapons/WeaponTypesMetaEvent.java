package sfgamedataeditor.views.main.modules.items.weapons;

import sfgamedataeditor.events.AbstractMetaEvent;
import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.EventCreator;
import sfgamedataeditor.events.ShowViewEvent;
import sfgamedataeditor.views.main.modules.items.ItemTypesView;

public class WeaponTypesMetaEvent extends AbstractMetaEvent {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addEvents() {
        ShowWeaponTypesListViewEvent event = EventCreator.createEvent(WeaponsTypesListView.class, ItemTypesView.class, ShowWeaponTypesListViewEvent.class);
        addEvent(event);
    }
}

class ShowWeaponTypesListViewEvent extends ShowViewEvent<WeaponsTypesListView, ItemTypesView, Object> {

    public ShowWeaponTypesListViewEvent(ClassTuple<WeaponsTypesListView, ItemTypesView> tuple) {
        super(tuple);
    }
}
