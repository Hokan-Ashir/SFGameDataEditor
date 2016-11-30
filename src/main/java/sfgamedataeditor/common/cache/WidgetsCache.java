package sfgamedataeditor.common.cache;

import sfgamedataeditor.common.widgets.AbstractWidget;
import sfgamedataeditor.common.widgets.AbstractWidgetListener;
import sfgamedataeditor.views.utility.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WidgetsCache {
    private final Map<Integer, List<Pair<AbstractWidget, AbstractWidgetListener>>> widgetMap = new HashMap<>();

    public boolean addWidgetListenerPair(Integer GUIComponentId, AbstractWidget widget, AbstractWidgetListener listener) {
        List<Pair<AbstractWidget, AbstractWidgetListener>> pairs = widgetMap.get(GUIComponentId);
        if (pairs == null) {
            pairs = new ArrayList<>();
            widgetMap.put(GUIComponentId, pairs);
        }

        return pairs.add(new Pair<>(widget, listener));
    }

    public boolean isWidgetExistsInCache(Integer GUIComponentId, Class<? extends AbstractWidget> widgetClass) {
        boolean isGUIComponentHaveCachedWidgets = widgetMap.containsKey(GUIComponentId);
        if (!isGUIComponentHaveCachedWidgets) {
            return false;
        } else {
            List<Pair<AbstractWidget, AbstractWidgetListener>> pairs = widgetMap.get(GUIComponentId);
            if (pairs == null) {
                return false;
            }

            for (Pair<AbstractWidget, AbstractWidgetListener> pair : pairs) {
                if (widgetClass.getClass().equals(pair.getKey().getClass())) {
                    return true;
                }
            }
        }

        return false;
    }

    public AbstractWidget getWidget(Integer GUIComponentId, Class<? extends AbstractWidget> widgetClass) {
        List<Pair<AbstractWidget, AbstractWidgetListener>> pairs = widgetMap.get(GUIComponentId);
        for (Pair<AbstractWidget, AbstractWidgetListener> pair : pairs) {
            if (pair.getKey().getClass().equals(widgetClass)) {
                return pair.getKey();
            }
        }

        throw new RuntimeException("No widget for class [" + widgetClass.getName() + "] found in cache [" + getClass().getName() + "]");
    }
}
