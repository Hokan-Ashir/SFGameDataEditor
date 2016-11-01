package sfgamedataeditor.events.processing;

import sfgamedataeditor.mvc.objects.AbstractController;
import sfgamedataeditor.views.common.ControllableView;

public class ViewControllerPair {
    private final ControllableView view;
    private final AbstractController controller;

    public ViewControllerPair(ControllableView view, AbstractController controller) {
        this.view = view;
        this.controller = controller;
    }

    public ControllableView getView() {
        return view;
    }

    public AbstractController getController() {
        return controller;
    }
}
