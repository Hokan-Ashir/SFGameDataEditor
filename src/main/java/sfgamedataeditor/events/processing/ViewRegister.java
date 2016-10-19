package sfgamedataeditor.events.processing;

import sfgamedataeditor.dataextraction.OffsetProvider;
import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.views.common.AbstractView;
import sfgamedataeditor.views.common.NullView;
import sfgamedataeditor.views.main.MainView;

import java.util.HashMap;
import java.util.Map;

public enum ViewRegister {
    INSTANCE;

    private Map<ClassTuple, AbstractView> views = new HashMap<ClassTuple, AbstractView>() {{
        put(new ClassTuple<>(NullView.class, AbstractView.class), new NullView(null));
    }};

    public Map<ClassTuple, AbstractView> getViews() {
        return views;
    }

    // TODO find more appropriate method-parameter signature
    public AbstractView getView(ClassTuple tuple) {
        if (views.containsKey(tuple)) {
            return views.get(tuple);
        }

        return null;
    }
}
