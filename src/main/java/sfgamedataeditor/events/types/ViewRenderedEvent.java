package sfgamedataeditor.events.types;

import sfgamedataeditor.views.common.ControllableView;

public class ViewRenderedEvent extends ViewEvent {
    public ViewRenderedEvent(Class<? extends ControllableView> classViewRendered) {
        super(classViewRendered);
    }
}
