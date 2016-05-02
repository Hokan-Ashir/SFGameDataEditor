package sfgamedataeditor.views.main;

import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.EventCreator;
import sfgamedataeditor.events.types.AbstractMetaEvent;
import sfgamedataeditor.events.types.ShowViewEvent;
import sfgamedataeditor.views.common.NullView;

public class MainViewMetaEvent extends AbstractMetaEvent {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addEvents() {
        ShowMainViewEvent event = EventCreator.createEvent(MainView.class, NullView.class, ShowMainViewEvent.class);
        addEvent(event);
    }
}

class ShowMainViewEvent extends ShowViewEvent<MainView, NullView, Object> {
    public ShowMainViewEvent(ClassTuple<MainView, NullView> tuple) {
        super(tuple);
    }
}
