package sfgamedataeditor.events.types;

import sfgamedataeditor.views.common.ControllableView;

public class ViewUnrenderedEvent extends ViewEvent {
    public ViewUnrenderedEvent(Class<? extends ControllableView> classViewUnrendered) {
        super(classViewUnrendered);
    }
}
