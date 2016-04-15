package sfgamedataeditor.views.common;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public enum ViewRegister {
    INSTANCE;

    private Map<Class<? extends AbstractView>, AbstractView> viewRegister = new HashMap<>();

    public <T extends AbstractView> AbstractView<T> getView(Class<? extends AbstractView> viewClass, T parentView) {
        AbstractView<T> resultView = null;
        if (!viewRegister.containsKey(viewClass)) {
            try {
                resultView = viewClass.getDeclaredConstructor(parentView.getClass()).newInstance(parentView);
            } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
            viewRegister.put(viewClass, resultView);
        } else {
            resultView = viewRegister.get(viewClass);
        }

        return resultView;
    }
}
