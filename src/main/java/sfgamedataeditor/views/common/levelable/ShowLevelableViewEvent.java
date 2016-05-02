package sfgamedataeditor.views.common.levelable;

import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.types.ShowViewEvent;
import sfgamedataeditor.views.common.AbstractView;

public class ShowLevelableViewEvent<T extends AbstractView> extends ShowViewEvent<LevelableView<T>, T, Object> {

    public ShowLevelableViewEvent(ClassTuple<LevelableView<T>, T> tuple) {
        super(tuple);
    }
}
