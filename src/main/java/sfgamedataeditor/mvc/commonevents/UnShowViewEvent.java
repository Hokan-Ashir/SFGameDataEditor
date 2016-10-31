package sfgamedataeditor.mvc.commonevents;

import sfgamedataeditor.events.types.Event;
import sfgamedataeditor.views.common.AbstractView;

public class UnShowViewEvent extends Event {

    private final Class<? extends AbstractView> classViewToUnShow;

    public UnShowViewEvent(Class<? extends AbstractView> classViewToUnShow) {
        this.classViewToUnShow = classViewToUnShow;
    }

    public Class<? extends AbstractView> getClassViewToUnShow() {
        return classViewToUnShow;
    }
}
