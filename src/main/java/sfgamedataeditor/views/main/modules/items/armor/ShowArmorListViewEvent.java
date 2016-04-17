package sfgamedataeditor.views.main.modules.items.armor;

import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.ShowViewEvent;
import sfgamedataeditor.views.main.modules.items.ItemTypesView;

public class ShowArmorListViewEvent extends ShowViewEvent<ArmorListView, ItemTypesView, Object> {

    public ShowArmorListViewEvent(ClassTuple<ArmorListView, ItemTypesView> tuple) {
        super(tuple);
    }
}
