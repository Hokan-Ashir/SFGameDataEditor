package sfgamedataeditor.mvc.commonevents;

import sfgamedataeditor.events.types.Event;
import sfgamedataeditor.mvc.objects.Model;
import sfgamedataeditor.views.common.RenderableView;

public class UpdateViewModelEvent extends Event {

    private final Model model;
    private final RenderableView view;

    public UpdateViewModelEvent(RenderableView view, Model model) {
        this.model = model;
        this.view = view;
    }

    public RenderableView getView() {
        return view;
    }

    public Model getModel() {
        return model;
    }
}
