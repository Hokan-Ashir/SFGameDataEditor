package sfgamedataeditor.views.main.modules.items.armor;

import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.EventCreator;
import sfgamedataeditor.events.types.AbstractMetaEvent;
import sfgamedataeditor.events.types.ClearViewEvent;
import sfgamedataeditor.events.types.SetModuleNameEvent;
import sfgamedataeditor.events.types.ShowViewEvent;
import sfgamedataeditor.views.main.MainView;
import sfgamedataeditor.views.main.modules.items.ItemTypesView;

public class ArmorTypesMetaEvent extends AbstractMetaEvent {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addEvents() {
        SetModuleNameEvent moduleNameEvent = new SetModuleNameEvent(ItemTypesView.class, MainView.class);
        ShowArmorTypeListViewEvent event = EventCreator.createEvent(ArmorTypeListView.class, MainView.class, ShowArmorTypeListViewEvent.class);
        ClearViewEvent clearViewEvent = new ClearViewEvent<>(ArmorTypeListView.class, MainView.class);
        addEvents(moduleNameEvent, event, clearViewEvent);
    }
}

class ShowArmorTypeListViewEvent extends ShowViewEvent<ArmorTypeListView, MainView, Object> {

    public ShowArmorTypeListViewEvent(ClassTuple<ArmorTypeListView, MainView> tuple) {
        super(tuple);
    }
}
