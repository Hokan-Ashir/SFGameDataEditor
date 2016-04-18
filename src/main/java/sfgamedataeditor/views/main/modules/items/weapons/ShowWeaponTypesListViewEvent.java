package sfgamedataeditor.views.main.modules.items.weapons;

import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.ShowViewEvent;
import sfgamedataeditor.views.main.modules.items.ItemTypesView;

public class ShowWeaponTypesListViewEvent extends ShowViewEvent<WeaponsTypesListView, ItemTypesView, Object> {

    public ShowWeaponTypesListViewEvent(ClassTuple<WeaponsTypesListView, ItemTypesView> tuple) {
        super(tuple);
    }
}
