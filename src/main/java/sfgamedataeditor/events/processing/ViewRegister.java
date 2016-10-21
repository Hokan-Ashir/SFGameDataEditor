package sfgamedataeditor.events.processing;

import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.views.common.AbstractView;
import sfgamedataeditor.views.common.NullView;

import java.util.HashMap;
import java.util.Map;

public enum ViewRegister {
    INSTANCE;

    private Map<ClassTuple<?, ?>, AbstractView> views = new HashMap<ClassTuple<?, ?>, AbstractView>() {{
        // add root to all views
        put(new ClassTuple<>(NullView.class, AbstractView.class), new NullView(null));
    }};

    public Map<ClassTuple<?, ?>, AbstractView> getViews() {
        return views;
    }

    public <T extends AbstractView<V>, V extends AbstractView> T getView(ClassTuple<T, V> tuple) {
        if (views.containsKey(tuple)) {
            return (T) views.get(tuple);
        }

        return null;
    }
}
