package sfgamedataeditor.events.types;

import sfgamedataeditor.views.common.ControllableView;

public class UnShowViewEvent extends Event {

    private final Class<? extends ControllableView> classViewToUnShow;

    public UnShowViewEvent(Class<? extends ControllableView> classViewToUnShow) {
        this.classViewToUnShow = classViewToUnShow;
    }

    public Class<? extends ControllableView> getClassViewToUnShow() {
        return classViewToUnShow;
    }
}
