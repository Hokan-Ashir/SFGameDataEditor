package sfgamedataeditor.events.types;

import sfgamedataeditor.mvc.objects.ControllableView;

public class ViewEvent extends Event {
    private final Class<? extends ControllableView> viewClass;

    public ViewEvent(Class<? extends ControllableView> viewClass) {
        this.viewClass = viewClass;
    }

    public Class<? extends ControllableView> getViewClass() {
        return viewClass;
    }
}
