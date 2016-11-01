package sfgamedataeditor.mvc.objects;

import sfgamedataeditor.views.common.ControllableView;

public abstract class AbstractController<M, V extends ControllableView> {
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

    public abstract void renderView();

    public abstract void unRenderView();
}
