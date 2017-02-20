package sfgamedataeditor.events.processing;

import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.mvc.objects.PresentableView;

public class ViewControllerPair<V extends PresentableView, C extends AbstractPresenter> {
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
