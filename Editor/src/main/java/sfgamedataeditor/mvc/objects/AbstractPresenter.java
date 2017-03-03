package sfgamedataeditor.mvc.objects;

public abstract class AbstractPresenter<P, V extends PresentableView> {
    private Model<P> model;
    private final V view;

    protected AbstractPresenter(V view) {
        this.view = view;
    }

    public void setModel(Model<P> model) {
        this.model = model;
    }

    public Model<P> getModel() {
        return model;
    }

    protected V getView() {
        return view;
    }

    public abstract void updateView();

    public abstract void renderView();

    public abstract void unRenderView();
}
