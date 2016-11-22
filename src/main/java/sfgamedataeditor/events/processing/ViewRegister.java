package sfgamedataeditor.events.processing;

import sfgamedataeditor.mvc.objects.ControllableView;

import java.util.HashMap;
import java.util.Map;

public enum ViewRegister {
    INSTANCE;

    private Map<Class<? extends ControllableView>, ViewControllerPair> views = new HashMap<>();

    public Map<Class<? extends ControllableView>, ViewControllerPair> getViews() {
        return views;
    }

    public <T extends ControllableView> T getView(Class<T> viewClass) {
        if (views.containsKey(viewClass)) {
            return (T) views.get(viewClass).getView();
        }

        return null;
    }
}
