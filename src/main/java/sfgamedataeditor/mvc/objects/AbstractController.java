package sfgamedataeditor.mvc.objects;

public abstract class AbstractController<M, V extends ControllableView> {
    private Model<M> model;
    private final V view;

    protected AbstractController(V view) {
        this.view = view;
    }

    public void setModel(Model<M> model) {
        this.model = model;
    }

    protected Model<M> getModel() {
        return model;
    }

    protected V getView() {
        return view;
    }

    public abstract void updateView();

    public abstract void renderView();

    public abstract void unRenderView();
}
