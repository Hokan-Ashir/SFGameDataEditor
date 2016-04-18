package sfgamedataeditor.views.main.modules.items.armor;

import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.ShowViewEvent;
import sfgamedataeditor.views.main.modules.items.ItemTypesView;

public class ShowArmorTypeListViewEvent extends ShowViewEvent<ArmorTypeListView, ItemTypesView, Object> {

    public ShowArmorTypeListViewEvent(ClassTuple<ArmorTypeListView, ItemTypesView> tuple) {
        super(tuple);
    }
}
