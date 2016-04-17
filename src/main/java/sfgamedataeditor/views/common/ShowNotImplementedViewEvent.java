package sfgamedataeditor.views.common;

import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.ShowViewEvent;

public class ShowNotImplementedViewEvent<T extends AbstractView> extends ShowViewEvent<NotImplementedView<T>, T, Object> {

    public ShowNotImplementedViewEvent(ClassTuple<NotImplementedView<T>, T> tuple) {
        super(tuple);
    }
}
