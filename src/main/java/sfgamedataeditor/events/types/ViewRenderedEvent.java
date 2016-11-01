package sfgamedataeditor.events.types;

import sfgamedataeditor.views.common.RenderableView;

public class ViewRenderedEvent extends Event {
    private Class<? extends RenderableView> classViewRendered;

    public ViewRenderedEvent(Class<? extends RenderableView> classViewRendered) {
        this.classViewRendered = classViewRendered;
    }

    public Class<? extends RenderableView> getClassViewRendered() {
        return classViewRendered;
    }
}
