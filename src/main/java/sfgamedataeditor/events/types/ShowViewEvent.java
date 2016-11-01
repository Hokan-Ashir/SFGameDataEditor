package sfgamedataeditor.events.types;

import sfgamedataeditor.mvc.objects.Model;
import sfgamedataeditor.views.common.RenderableView;

public class ShowViewEvent extends Event {
    private final Class<? extends RenderableView> classViewToShow;
    private final Model model;

    public ShowViewEvent(Class<? extends RenderableView> classViewToShow, Model model) {
        this.classViewToShow = classViewToShow;
        this.model = model;
    }

    public Model getModel() {
        return model;
    }

    public Class<? extends RenderableView> getClassViewToShow() {
        return classViewToShow;
    }
}
