package sfgamedataeditor.views.main.modules.items.weapons;

import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.EventCreator;
import sfgamedataeditor.events.types.AbstractViewableMetaEvent;
import sfgamedataeditor.events.types.ClearViewEvent;
import sfgamedataeditor.events.types.SetModuleNameEvent;
import sfgamedataeditor.events.types.ShowViewEvent;
import sfgamedataeditor.views.main.MainView;
import sfgamedataeditor.views.main.modules.items.ItemTypesMetaEvent;
import sfgamedataeditor.views.main.modules.items.ItemTypesView;

public class WeaponTypesMetaEvent extends AbstractViewableMetaEvent<ItemTypesMetaEvent> {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addEvents() {
        SetModuleNameEvent moduleNameEvent = new SetModuleNameEvent(ItemTypesView.class, MainView.class);
        ShowWeaponTypesListViewEvent event = EventCreator.createEvent(WeaponsTypesListView.class, MainView.class, ShowWeaponTypesListViewEvent.class);
        ClearViewEvent clearViewEvent = new ClearViewEvent<>(WeaponsTypesListView.class, MainView.class);
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

class ShowWeaponTypesListViewEvent extends ShowViewEvent<WeaponsTypesListView, MainView, Object> {

    public ShowWeaponTypesListViewEvent(ClassTuple<WeaponsTypesListView, MainView> tuple) {
        super(tuple);
    }
}
