package sfgamedataeditor.views.main.modules.items.miscellaneous;

import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.EventCreator;
import sfgamedataeditor.events.types.AbstractMetaEvent;
import sfgamedataeditor.events.types.ClearViewEvent;
import sfgamedataeditor.events.types.ShowViewEvent;
import sfgamedataeditor.views.main.modules.items.ItemTypesView;

public class MiscellaneousMetaEvent extends AbstractMetaEvent {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addEvents() {
        ShowMiscellaneousViewEvent event = EventCreator.createEvent(MiscellaneousListView.class, ItemTypesView.class, ShowMiscellaneousViewEvent.class);
        ClearViewEvent clearViewEvent = new ClearViewEvent(MiscellaneousListView.class, ItemTypesView.class);
        addEvents(event, clearViewEvent);
    }
}

class ShowMiscellaneousViewEvent extends ShowViewEvent<MiscellaneousListView, ItemTypesView, Object> {

    public ShowMiscellaneousViewEvent(ClassTuple<MiscellaneousListView, ItemTypesView> tuple) {
        super(tuple);
    }
}
