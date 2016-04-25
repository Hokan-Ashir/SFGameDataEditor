package sfgamedataeditor.events;

import sfgamedataeditor.views.common.AbstractView;

public class ShowViewEvent<T extends AbstractView<V>, V extends AbstractView, P> {

    private ClassTuple<T, V> tuple;
    private P objectParameter;
    private String description;

    public ShowViewEvent(ClassTuple<T, V> tuple) {
        this.tuple = tuple;
    }

    public ClassTuple<T, V> getTuple() {
        return tuple;
    }

    public P getObjectParameter() {
        return objectParameter;
    }

    public void setObjectParameter(P objectParameter) {
        this.objectParameter = objectParameter;
    }

    // TODO override in child events, maybe make abstract
    protected void setDescription(String description) {
        this.description = description;
    }

    public String getEventDescription() {
        return description;
    }
}
