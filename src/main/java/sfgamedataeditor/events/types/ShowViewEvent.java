package sfgamedataeditor.events.types;

import sfgamedataeditor.mvc.objects.Model;
import sfgamedataeditor.views.common.ControllableView;

public class ShowViewEvent extends ViewEvent {
    private final Model model;

    public ShowViewEvent(Class<? extends ControllableView> classViewToShow, Model model) {
        super(classViewToShow);
        this.model = model;
    }

    public Model getModel() {
        return model;
    }
}
