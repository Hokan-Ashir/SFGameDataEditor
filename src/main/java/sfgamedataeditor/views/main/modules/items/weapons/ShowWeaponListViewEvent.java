package sfgamedataeditor.views.main.modules.items.weapons;

import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.ShowViewEvent;
import sfgamedataeditor.views.main.modules.items.ItemTypesView;

public class ShowWeaponListViewEvent extends ShowViewEvent<WeaponsListView, ItemTypesView, Object> {

    public ShowWeaponListViewEvent(ClassTuple<WeaponsListView, ItemTypesView> tuple) {
        super(tuple);
    }
}
