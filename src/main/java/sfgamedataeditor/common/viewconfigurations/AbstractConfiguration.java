package sfgamedataeditor.common.viewconfigurations;

import sfgamedataeditor.common.widgets.AbstractWidget;
import sfgamedataeditor.databind.Pair;
import sfgamedataeditor.fieldwrapping.AbstractFieldListener;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractConfiguration {

    private Map<Integer, Pair<Class<? extends AbstractWidget>, Class<? extends AbstractFieldListener>>> widgetMap = new HashMap<>();

    public AbstractConfiguration() {
        fillConfigurationMappings();
    }

    protected abstract void fillConfigurationMappings();

    protected void addViewMapping(Integer GUIComponentId, Pair<Class<? extends AbstractWidget>, Class<? extends AbstractFieldListener>> widget) {
        widgetMap.put(GUIComponentId, widget);
    }

    public Class<? extends AbstractWidget> getWidgetClass(Integer GUIComponentId) {
        Pair<Class<? extends AbstractWidget>, Class<? extends AbstractFieldListener>> pair = widgetMap.get(GUIComponentId);
        if (pair != null) {
            return pair.getKey();
        }

        throw new RuntimeException("No widget class found for GUIComponentId [" + GUIComponentId + "]");
    }

    public Class<? extends AbstractFieldListener> getListenerClass(Integer GUIComponentId) {
        Pair<Class<? extends AbstractWidget>, Class<? extends AbstractFieldListener>> pair = widgetMap.get(GUIComponentId);
        if (pair != null) {
            return pair.getValue();
        }

        throw new RuntimeException("No widget class found for GUIComponentId [" + GUIComponentId + "]");
    }
}
