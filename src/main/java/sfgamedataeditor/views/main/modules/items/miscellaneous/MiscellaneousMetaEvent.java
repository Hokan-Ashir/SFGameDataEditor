package sfgamedataeditor.views.main.modules.items.miscellaneous;

import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.EventCreator;
import sfgamedataeditor.events.types.AbstractMetaEvent;
import sfgamedataeditor.events.types.ClearViewEvent;
import sfgamedataeditor.events.types.SetModuleNameEvent;
import sfgamedataeditor.events.types.ShowViewEvent;
import sfgamedataeditor.views.main.MainView;
import sfgamedataeditor.views.main.modules.items.ItemTypesView;

public class MiscellaneousMetaEvent extends AbstractMetaEvent {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addEvents() {
        SetModuleNameEvent moduleNameEvent = new SetModuleNameEvent(ItemTypesView.class, MainView.class);
        ShowMiscellaneousViewEvent event = EventCreator.createEvent(MiscellaneousListView.class, MainView.class, ShowMiscellaneousViewEvent.class);
        ClearViewEvent clearViewEvent = new ClearViewEvent<>(MiscellaneousListView.class, MainView.class);
        addEvents(moduleNameEvent, event, clearViewEvent);
    }
}

class ShowMiscellaneousViewEvent extends ShowViewEvent<MiscellaneousListView, MainView, Object> {

    public ShowMiscellaneousViewEvent(ClassTuple<MiscellaneousListView, MainView> tuple) {
        super(tuple);
    }
}
