package sfgamedataeditor.events.processing;

import sfgamedataeditor.views.common.RenderableView;

import java.util.HashMap;
import java.util.Map;

public enum ViewRegister {
    INSTANCE;

    private Map<Class<? extends RenderableView>, ViewControllerPair> views = new HashMap<>();

    public Map<Class<? extends RenderableView>, ViewControllerPair> getViews() {
        return views;
    }

    public <T extends RenderableView> T getView(Class<T> viewClass) {
        if (views.containsKey(viewClass)) {
            return (T) views.get(viewClass).getView();
        }

        return null;
    }
}
