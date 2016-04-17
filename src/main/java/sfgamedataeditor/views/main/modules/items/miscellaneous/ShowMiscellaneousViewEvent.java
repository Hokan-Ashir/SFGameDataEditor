package sfgamedataeditor.views.main.modules.items.miscellaneous;

import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.ShowViewEvent;
import sfgamedataeditor.views.main.modules.items.ItemTypesView;

public class ShowMiscellaneousViewEvent extends ShowViewEvent<MiscellaneousListView, ItemTypesView, Object> {

    public ShowMiscellaneousViewEvent(ClassTuple<MiscellaneousListView, ItemTypesView> tuple) {
        super(tuple);
    }
}
