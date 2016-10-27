package sfgamedataeditor.events.types;

import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.processing.ViewRegister;
import sfgamedataeditor.views.common.AbstractView;

public class ClearViewEvent<C extends AbstractView<P>, P extends AbstractView> extends Event {
    private Class<C> childViewClass;
    private Class<P> parentViewClass;

    public ClearViewEvent(Class<C> childViewClass, Class<P> parentViewClass) {
        this.childViewClass = childViewClass;
        this.parentViewClass = parentViewClass;
    }

    public AbstractView getView() {
        return ViewRegister.INSTANCE.getView(new ClassTuple<>(childViewClass, parentViewClass));
    }
}
