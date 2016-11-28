package sfgamedataeditor.events.processing;

import sfgamedataeditor.mvc.objects.AbstractController;
import sfgamedataeditor.mvc.objects.ControllableView;

public class ViewControllerPair<V extends ControllableView, C extends AbstractController> {
    private final V view;
    private final C controller;

    public ViewControllerPair(V view, C controller) {
        this.view = view;
        this.controller = controller;
    }

    public V getView() {
        return view;
    }

    public C getController() {
        return controller;
    }
}
