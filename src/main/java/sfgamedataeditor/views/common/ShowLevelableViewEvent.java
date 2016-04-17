package sfgamedataeditor.views.common;

import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.ShowViewEvent;

public class ShowLevelableViewEvent<T extends AbstractView> extends ShowViewEvent<LevelableView<T>, T, Object> {

    public ShowLevelableViewEvent(ClassTuple<LevelableView<T>, T> tuple) {
        super(tuple);
    }
}
