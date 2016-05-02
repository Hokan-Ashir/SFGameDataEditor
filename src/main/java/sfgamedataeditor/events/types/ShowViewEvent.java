package sfgamedataeditor.events.types;

import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.views.common.AbstractView;

public class ShowViewEvent<T extends AbstractView<V>, V extends AbstractView, P> extends Event {

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
}
