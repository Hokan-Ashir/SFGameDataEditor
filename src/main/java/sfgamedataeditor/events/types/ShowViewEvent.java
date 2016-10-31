package sfgamedataeditor.events.types;

import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.views.common.AbstractView;

public class ShowViewEvent<T extends AbstractView, V extends AbstractView, P> extends Event<P> {

    private ClassTuple<T, V> tuple;

    public ShowViewEvent(ClassTuple<T, V> tuple) {
        this.tuple = tuple;
    }

    public ClassTuple<T, V> getTuple() {
        return tuple;
    }
}
