package sfgamedataeditor.events.processing;

import sfgamedataeditor.mvc.objects.AbstractController;
import sfgamedataeditor.views.common.RenderableView;

public class ViewControllerPair {
    private final RenderableView view;
    private final AbstractController controller;

    public ViewControllerPair(RenderableView view, AbstractController controller) {
        this.view = view;
        this.controller = controller;
    }

    public RenderableView getView() {
        return view;
    }

    public AbstractController getController() {
        return controller;
    }
}
