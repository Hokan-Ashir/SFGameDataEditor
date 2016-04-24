package sfgamedataeditor.events;

import sfgamedataeditor.views.common.AbstractView;

public class ShowViewEvent<T extends AbstractView<V>, V extends AbstractView, P> {

    private ClassTuple<T, V> tuple;
    private P objectParameter;

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

    public String getEventDescription() {
        // TODO override in child events
        return "";
    }
}
