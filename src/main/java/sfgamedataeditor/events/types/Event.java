package sfgamedataeditor.events.types;

public class Event<P> {
    private String description;
    private P objectParameter;

    public String getDescription() {
        return description;
    }

    // TODO override in child events, maybe make abstract
    public void setDescription(String description) {
        this.description = description;
    }

    public P getObjectParameter() {
        return objectParameter;
    }

    public void setObjectParameter(P objectParameter) {
        this.objectParameter = objectParameter;
    }
}
