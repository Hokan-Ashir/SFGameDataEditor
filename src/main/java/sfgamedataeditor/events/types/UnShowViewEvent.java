package sfgamedataeditor.events.types;

import sfgamedataeditor.views.common.ControllableView;

public class UnShowViewEvent extends ViewEvent {
    public UnShowViewEvent(Class<? extends ControllableView> classViewToUnShow) {
        super(classViewToUnShow);
    }
}
