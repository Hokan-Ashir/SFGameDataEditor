package sfgamedataeditor.mvc.objects;

import sfgamedataeditor.views.common.RenderableView;

public abstract class AbstractController<M, V extends RenderableView> {
    private Model<M> model;
    private final V view;

    public AbstractController(V view) {
        this.view = view;
    }

    public void setModel(Model<M> model) {
        this.model = model;
    }

    public Model<M> getModel() {
        return model;
    }

    public V getView() {
        return view;
    }

    public abstract void updateView();
}
