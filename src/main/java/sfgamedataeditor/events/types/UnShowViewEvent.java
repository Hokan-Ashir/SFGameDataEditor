package sfgamedataeditor.events.types;

import sfgamedataeditor.views.common.RenderableView;

public class UnShowViewEvent extends Event {

    private final Class<? extends RenderableView> classViewToUnShow;

    public UnShowViewEvent(Class<? extends RenderableView> classViewToUnShow) {
        this.classViewToUnShow = classViewToUnShow;
    }

    public Class<? extends RenderableView> getClassViewToUnShow() {
        return classViewToUnShow;
    }
}
