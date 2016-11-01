package sfgamedataeditor.events.types;

import sfgamedataeditor.views.common.RenderableView;

public class ViewUnrenderedEvent extends Event {

    private Class<? extends RenderableView> classViewUnrendered;

    public ViewUnrenderedEvent(Class<? extends RenderableView> classViewUnrendered) {
        this.classViewUnrendered = classViewUnrendered;
    }

    public Class<? extends RenderableView> getClassViewUnrendered() {
        return classViewUnrendered;
    }
}
