package sfgamedataeditor.events.types;

import sfgamedataeditor.mvc.objects.ControllableView;
import sfgamedataeditor.mvc.objects.Model;

public class UpdateViewContentEvent extends ViewEvent {

    private final Model model;

    public UpdateViewContentEvent(Class<? extends ControllableView> viewClass, Model model) {
        super(viewClass);
        this.model = model;
    }

    public Model getModel() {
        return model;
    }
}
