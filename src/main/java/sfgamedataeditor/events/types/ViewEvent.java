package sfgamedataeditor.events.types;

import sfgamedataeditor.mvc.objects.PresentableView;

public class ViewEvent extends Event {
    private final Class<? extends PresentableView> viewClass;

    public ViewEvent(Class<? extends PresentableView> viewClass) {
        this.viewClass = viewClass;
    }

    public Class<? extends PresentableView> getViewClass() {
        return viewClass;
    }
}
