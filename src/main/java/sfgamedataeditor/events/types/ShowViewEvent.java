package sfgamedataeditor.events.types;

import sfgamedataeditor.mvc.objects.Model;
import sfgamedataeditor.views.common.ControllableView;

public class ShowViewEvent extends Event {
    private final Class<? extends ControllableView> classViewToShow;
    private final Model model;

    public ShowViewEvent(Class<? extends ControllableView> classViewToShow, Model model) {
        this.classViewToShow = classViewToShow;
        this.model = model;
    }

    public Model getModel() {
        return model;
    }

    public Class<? extends ControllableView> getClassViewToShow() {
        return classViewToShow;
    }
}
