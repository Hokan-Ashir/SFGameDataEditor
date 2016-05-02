package sfgamedataeditor.views.main.modules.items.armor;

import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.EventCreator;
import sfgamedataeditor.events.types.AbstractMetaEvent;
import sfgamedataeditor.events.types.ClearViewEvent;
import sfgamedataeditor.events.types.ShowViewEvent;
import sfgamedataeditor.views.main.modules.items.ItemTypesView;

public class ArmorTypesMetaEvent extends AbstractMetaEvent {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addEvents() {
        ShowArmorTypeListViewEvent event = EventCreator.createEvent(ArmorTypeListView.class, ItemTypesView.class, ShowArmorTypeListViewEvent.class);
        ClearViewEvent clearViewEvent = new ClearViewEvent(ArmorTypeListView.class, ItemTypesView.class);
        addEvents(event, clearViewEvent);
    }
}

class ShowArmorTypeListViewEvent extends ShowViewEvent<ArmorTypeListView, ItemTypesView, Object> {

    public ShowArmorTypeListViewEvent(ClassTuple<ArmorTypeListView, ItemTypesView> tuple) {
        super(tuple);
    }
}
