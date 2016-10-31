package sfgamedataeditor.mvc.commonevents;

import sfgamedataeditor.events.types.Event;
import sfgamedataeditor.views.common.RenderableView;

public class ShowViewEvent extends Event {
    private final Class<? extends RenderableView> classViewToShow;

    public ShowViewEvent(Class<? extends RenderableView> classViewToShow) {
        this.classViewToShow = classViewToShow;
    }

    public Class<? extends RenderableView> getClassViewToShow() {
        return classViewToShow;
    }
}
