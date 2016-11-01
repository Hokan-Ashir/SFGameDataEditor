package sfgamedataeditor.events.types;

import sfgamedataeditor.mvc.objects.Model;
import sfgamedataeditor.views.common.RenderableView;

public class UpdateViewModelEvent extends Event {

    private final Model model;
    private final Class<? extends RenderableView> classViewToUpdate;

    public UpdateViewModelEvent(Class<? extends RenderableView> classViewToUpdate, Model model) {
        this.model = model;
        this.classViewToUpdate = classViewToUpdate;
    }

    public Class<? extends RenderableView> getView() {
        return classViewToUpdate;
    }

    public Model getModel() {
        return model;
    }
}
