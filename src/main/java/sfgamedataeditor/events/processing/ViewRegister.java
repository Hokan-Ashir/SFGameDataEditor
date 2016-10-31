package sfgamedataeditor.events.processing;

import sfgamedataeditor.views.common.AbstractView;

import java.util.HashMap;
import java.util.Map;

public enum ViewRegister {
    INSTANCE;

    private Map<Class<? extends AbstractView>, AbstractView> views = new HashMap<>();

    public Map<Class<? extends AbstractView>, AbstractView> getViews() {
        return views;
    }

    public <T extends AbstractView> T getView(Class<T> viewClass) {
        if (views.containsKey(viewClass)) {
            return (T) views.get(viewClass);
        }

        return null;
    }
}
