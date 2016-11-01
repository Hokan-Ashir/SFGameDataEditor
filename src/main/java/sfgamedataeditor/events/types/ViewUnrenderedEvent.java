package sfgamedataeditor.events.types;

import sfgamedataeditor.views.common.ControllableView;

public class ViewUnrenderedEvent extends Event {

    private Class<? extends ControllableView> classViewUnrendered;

    public ViewUnrenderedEvent(Class<? extends ControllableView> classViewUnrendered) {
        this.classViewUnrendered = classViewUnrendered;
    }

    public Class<? extends ControllableView> getClassViewUnrendered() {
        return classViewUnrendered;
    }
}
