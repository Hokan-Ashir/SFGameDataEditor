package sfgamedataeditor.views.main.modules.items.miscellaneous;

import sfgamedataeditor.events.AbstractMetaEvent;
import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.EventCreator;
import sfgamedataeditor.events.ShowViewEvent;
import sfgamedataeditor.views.main.modules.items.ItemTypesView;

public class MiscellaneousMetaEvent extends AbstractMetaEvent {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addEvents() {
        ShowMiscellaneousViewEvent event = EventCreator.createEvent(MiscellaneousListView.class, ItemTypesView.class, ShowMiscellaneousViewEvent.class);
        addEvent(event);
    }
}

class ShowMiscellaneousViewEvent extends ShowViewEvent<MiscellaneousListView, ItemTypesView, Object> {

    public ShowMiscellaneousViewEvent(ClassTuple<MiscellaneousListView, ItemTypesView> tuple) {
        super(tuple);
    }
}
