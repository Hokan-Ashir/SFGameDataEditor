package sfgamedataeditor.events.types;

import sfgamedataeditor.views.common.ControllableView;

public class ViewRenderedEvent extends Event {
    private Class<? extends ControllableView> classViewRendered;

    public ViewRenderedEvent(Class<? extends ControllableView> classViewRendered) {
        this.classViewRendered = classViewRendered;
    }

    public Class<? extends ControllableView> getClassViewRendered() {
        return classViewRendered;
    }
}
