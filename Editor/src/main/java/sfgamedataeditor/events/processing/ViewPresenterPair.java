package sfgamedataeditor.events.processing;

import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.mvc.objects.PresentableView;

public class ViewPresenterPair<V extends PresentableView, P extends AbstractPresenter> {
    private final V view;
    private final P presenter;

    public ViewPresenterPair(V view, P presenter) {
        this.view = view;
        this.presenter = presenter;
    }

    public V getView() {
        return view;
    }

    public P getPresenter() {
        return presenter;
    }
}
