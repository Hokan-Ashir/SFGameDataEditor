package sfgamedataeditor.mvc.commonevents;

import sfgamedataeditor.events.types.Event;
import sfgamedataeditor.views.common.AbstractView;

public class ShowViewEvent extends Event {
    private final Class<? extends AbstractView> classViewToShow;

    public ShowViewEvent(Class<? extends AbstractView> classViewToShow) {
        this.classViewToShow = classViewToShow;
    }

    public Class<? extends AbstractView> getClassViewToShow() {
        return classViewToShow;
    }
}
