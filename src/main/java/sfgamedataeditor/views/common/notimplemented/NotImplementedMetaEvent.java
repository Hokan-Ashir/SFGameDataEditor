package sfgamedataeditor.views.common.notimplemented;

import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.EventCreator;
import sfgamedataeditor.events.types.AbstractMetaEvent;
import sfgamedataeditor.events.types.ShowViewEvent;
import sfgamedataeditor.views.common.AbstractView;
import sfgamedataeditor.views.common.races.AbstractRacesView;

public class NotImplementedMetaEvent extends AbstractMetaEvent {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addEvents() {
        ShowNotImplementedViewEvent event = EventCreator.createEvent(NotImplementedView.class, AbstractRacesView.class, ShowNotImplementedViewEvent.class);
        addEvent(event);
    }
}

class ShowNotImplementedViewEvent<T extends AbstractView> extends ShowViewEvent<NotImplementedView<T>, T, Object> {

    public ShowNotImplementedViewEvent(ClassTuple<NotImplementedView<T>, T> tuple) {
        super(tuple);
    }
}
