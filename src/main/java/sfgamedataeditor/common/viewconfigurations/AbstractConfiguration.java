package sfgamedataeditor.common.viewconfigurations;

import sfgamedataeditor.common.widgets.AbstractWidget;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractConfiguration {

    private Map<Integer, Class<? extends AbstractWidget>> widgetMap = new HashMap<>();

    public AbstractConfiguration() {
        fillConfigurationMappings();
    }

    protected abstract void fillConfigurationMappings();

    protected void addViewMapping(Integer GUIComponentId, Class<? extends AbstractWidget> widget) {
        widgetMap.put(GUIComponentId, widget);
    }

    public Class<? extends AbstractWidget> getWidgetClass(Integer GUIComponentId) {
        Class<? extends AbstractWidget> aClass = widgetMap.get(GUIComponentId);
        if (aClass != null) {
            return aClass;
        }

        throw new RuntimeException("No widget class found for GUIComponentId [" + GUIComponentId + "]");
    }
}
