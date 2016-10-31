package sfgamedataeditor.mvc.objects;

import sfgamedataeditor.views.common.AbstractView;

public abstract class AbstractController<M, V extends AbstractView> {
    private final Model<M> model;
    private final V view;

    public AbstractController(Model<M> model, V view) {
        this.model = model;
        this.view = view;
    }

    public Model<M> getModel() {
        return model;
    }

    public V getView() {
        return view;
    }

    public abstract void updateView();
}
