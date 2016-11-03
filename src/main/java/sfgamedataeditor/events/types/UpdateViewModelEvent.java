package sfgamedataeditor.events.types;

import sfgamedataeditor.mvc.objects.Model;
import sfgamedataeditor.views.common.ControllableView;

public class UpdateViewModelEvent extends ViewEvent {

    private final Model model;

    public UpdateViewModelEvent(Class<? extends ControllableView> classViewToUpdate, Model model) {
        super(classViewToUpdate);
        this.model = model;
    }

    public Model getModel() {
        return model;
    }
}
