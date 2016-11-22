package sfgamedataeditor.common.cache;

import sfgamedataeditor.common.widgets.AbstractWidget;
import sfgamedataeditor.common.widgets.Widget;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WidgetsCache {
    private Map<Integer, List<AbstractWidget>> widgetMap = new HashMap<>();

    public boolean addWidget(Integer GUIComponentId, AbstractWidget widget) {
        List<AbstractWidget> widgets = widgetMap.get(GUIComponentId);
        if (widgets == null) {
            widgets = new ArrayList<>();
            widgetMap.put(GUIComponentId, widgets);
        }

        return widgets.add(widget);
    }

    public boolean isWidgetExistsInCache(Integer GUIComponentId, Class<? extends AbstractWidget> widgetClass) {
        boolean isGUIComponentHaveCachedWidgets = widgetMap.containsKey(GUIComponentId);
        if (isGUIComponentHaveCachedWidgets) {
            return true;
        } else {
            List<AbstractWidget> widgets = widgetMap.get(GUIComponentId);
            if (widgets == null) {
                return false;
            }

            for (Widget innerWidget : widgets) {
                if (widgetClass.getClass().equals(innerWidget.getClass())) {
                    return true;
                }
            }
        }

        return false;
    }

    public AbstractWidget getWidget(Integer GUIComponentId, Class<? extends AbstractWidget> widgetClass) {
        List<AbstractWidget> widgets = widgetMap.get(GUIComponentId);
        for (AbstractWidget widget : widgets) {
            if (widget.getClass().equals(widgetClass)) {
                return widget;
            }
        }

        throw new RuntimeException("No widget for class [" + widgetClass.getName() + "] found in cache [" + getClass().getName() + "]");
    }
}
