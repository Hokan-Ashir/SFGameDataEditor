package sfgamedataeditor.events.types;

import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.processing.ViewRegister;
import sfgamedataeditor.views.common.AbstractView;

public class ClearViewEvent extends Event {
    private Class<? extends AbstractView> childViewClass;
    private Class<? extends AbstractView> parentViewClass;

    public ClearViewEvent(Class<? extends AbstractView> childViewClass, Class<? extends AbstractView> parentViewClass) {
        this.childViewClass = childViewClass;
        this.parentViewClass = parentViewClass;
    }

    public AbstractView getView() {
        return ViewRegister.INSTANCE.getView(new ClassTuple<>(childViewClass, parentViewClass));
    }
}
