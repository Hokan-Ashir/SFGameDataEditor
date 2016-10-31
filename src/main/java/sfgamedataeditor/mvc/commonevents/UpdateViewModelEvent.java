package sfgamedataeditor.mvc.commonevents;

import sfgamedataeditor.events.types.Event;
import sfgamedataeditor.mvc.objects.Model;
import sfgamedataeditor.views.common.AbstractView;

public class UpdateViewModelEvent extends Event {

    private final Model model;
    private final AbstractView view;

    public UpdateViewModelEvent(AbstractView view, Model model) {
        this.model = model;
        this.view = view;
    }

    public AbstractView getView() {
        return view;
    }

    public Model getModel() {
        return model;
    }
}
