package sfgamedataeditor.events.types;

import sfgamedataeditor.mvc.objects.ControllableView;

public class UpdateViewContentEvent extends ViewEvent {

    public UpdateViewContentEvent(Class<? extends ControllableView> viewClass) {
        super(viewClass);
    }
}
