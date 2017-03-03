package sfgamedataeditor.events.processing;

import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.mvc.objects.PresentableView;

import java.util.HashMap;
import java.util.Map;

public enum ViewRegister {
    INSTANCE;

    private final Map<Class<? extends PresentableView>, ViewPresenterPair> views = new HashMap<>();

    public Map<Class<? extends PresentableView>, ViewPresenterPair> getViews() {
        return views;
    }

    public <T extends PresentableView> T getView(Class<T> viewClass) {
        if (views.containsKey(viewClass)) {
            return (T) views.get(viewClass).getView();
        }

        return null;
    }

    public <T extends AbstractPresenter, V extends PresentableView> T getPresenter(Class<V> viewClass, Class<T> presenterClass) {
        if (views.containsKey(viewClass)) {
            return (T) views.get(viewClass).getPresenter();
        }

        return null;
    }
}
