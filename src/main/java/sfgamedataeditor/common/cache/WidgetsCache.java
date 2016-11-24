package sfgamedataeditor.common.cache;

import sfgamedataeditor.common.widgets.AbstractWidget;
import sfgamedataeditor.databind.Pair;
import sfgamedataeditor.fieldwrapping.AbstractFieldListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WidgetsCache {
    private Map<Integer, List<Pair<AbstractWidget, AbstractFieldListener>>> widgetMap = new HashMap<>();

    public boolean addWidgetListenerPair(Integer GUIComponentId, AbstractWidget widget, AbstractFieldListener listener) {
        List<Pair<AbstractWidget, AbstractFieldListener>> pairs = widgetMap.get(GUIComponentId);
        if (pairs == null) {
            pairs = new ArrayList<>();
            widgetMap.put(GUIComponentId, pairs);
        }

        return pairs.add(new Pair<>(widget, listener));
    }

    public boolean isWidgetExistsInCache(Integer GUIComponentId, Class<? extends AbstractWidget> widgetClass) {
        boolean isGUIComponentHaveCachedWidgets = widgetMap.containsKey(GUIComponentId);
        if (isGUIComponentHaveCachedWidgets) {
            return true;
        } else {
            List<Pair<AbstractWidget, AbstractFieldListener>> pairs = widgetMap.get(GUIComponentId);
            if (pairs == null) {
                return false;
            }

            for (Pair<AbstractWidget, AbstractFieldListener> pair : pairs) {
                if (widgetClass.getClass().equals(pair.getKey().getClass())) {
                    return true;
                }
            }
        }

        return false;
    }

    public AbstractWidget getWidget(Integer GUIComponentId, Class<? extends AbstractWidget> widgetClass) {
        List<Pair<AbstractWidget, AbstractFieldListener>> pairs = widgetMap.get(GUIComponentId);
        for (Pair<AbstractWidget, AbstractFieldListener> pair : pairs) {
            if (pair.getKey().getClass().equals(widgetClass)) {
                return pair.getKey();
            }
        }

        throw new RuntimeException("No widget for class [" + widgetClass.getName() + "] found in cache [" + getClass().getName() + "]");
    }

    public AbstractFieldListener getListener(Integer GUIComponentId, Class<? extends AbstractFieldListener> listenerClass) {
        List<Pair<AbstractWidget, AbstractFieldListener>> pairs = widgetMap.get(GUIComponentId);
        for (Pair<AbstractWidget, AbstractFieldListener> pair : pairs) {
            if (pair.getValue().getClass().equals(listenerClass)) {
                return pair.getValue();
            }
        }

        throw new RuntimeException("No listener for class [" + listenerClass.getName() + "] found in cache [" + getClass().getName() + "]");
    }
}
