package sfgamedataeditor.views.common.notimplemented;

import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.ShowViewEvent;
import sfgamedataeditor.views.common.AbstractView;

public class ShowNotImplementedViewEvent<T extends AbstractView> extends ShowViewEvent<NotImplementedView<T>, T, Object> {

    public ShowNotImplementedViewEvent(ClassTuple<NotImplementedView<T>, T> tuple) {
        super(tuple);
    }
}
