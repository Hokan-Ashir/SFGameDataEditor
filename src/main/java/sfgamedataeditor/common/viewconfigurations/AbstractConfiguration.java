package sfgamedataeditor.common.viewconfigurations;

import sfgamedataeditor.common.widgets.AbstractWidget;
import sfgamedataeditor.common.widgets.AbstractWidgetListener;
import sfgamedataeditor.databind.Pair;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractConfiguration {

    private final Map<Integer, Pair<Class<? extends AbstractWidget>, Class<? extends AbstractWidgetListener>>> widgetMap = new HashMap<>();

    protected AbstractConfiguration() {
        fillConfigurationMappings();
    }

    protected abstract void fillConfigurationMappings();

    protected void addViewMapping(Integer GUIComponentId, Pair<Class<? extends AbstractWidget>, Class<? extends AbstractWidgetListener>> widget) {
        widgetMap.put(GUIComponentId, widget);
    }

    public Class<? extends AbstractWidget> getWidgetClass(Integer GUIComponentId) {
        Pair<Class<? extends AbstractWidget>, Class<? extends AbstractWidgetListener>> pair = widgetMap.get(GUIComponentId);
        if (pair != null) {
            return pair.getKey();
        }

        throw new RuntimeException("No widget class found for GUIComponentId [" + GUIComponentId + "]");
    }

    public Class<? extends AbstractWidgetListener> getListenerClass(Integer GUIComponentId) {
        Pair<Class<? extends AbstractWidget>, Class<? extends AbstractWidgetListener>> pair = widgetMap.get(GUIComponentId);
        if (pair != null) {
            return pair.getValue();
        }

        throw new RuntimeException("No widget class found for GUIComponentId [" + GUIComponentId + "]");
    }
}
