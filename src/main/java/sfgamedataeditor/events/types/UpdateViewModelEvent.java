package sfgamedataeditor.events.types;

import sfgamedataeditor.mvc.objects.Model;
import sfgamedataeditor.views.common.ControllableView;

public class UpdateViewModelEvent extends Event {

    private final Model model;
    private final Class<? extends ControllableView> classViewToUpdate;

    public UpdateViewModelEvent(Class<? extends ControllableView> classViewToUpdate, Model model) {
        this.model = model;
        this.classViewToUpdate = classViewToUpdate;
    }

    public Class<? extends ControllableView> getView() {
        return classViewToUpdate;
    }

    public Model getModel() {
        return model;
    }
}
