package sfgamedataeditor.mvc.objects;

public class Model<T> {

    private final T parameter;

    public Model(T parameter) {
        this.parameter = parameter;
    }

    public T getParameter() {
        return parameter;
    }
}
