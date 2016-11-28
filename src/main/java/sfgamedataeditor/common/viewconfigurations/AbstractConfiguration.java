package sfgamedataeditor.common.viewconfigurations;

import sfgamedataeditor.common.widgets.AbstractWidget;
import sfgamedataeditor.common.widgets.AbstractWidgetListener;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractConfiguration {

    private final Map<Integer, ConfigurationWidgetParameter> widgetMap = new HashMap<>();

    protected AbstractConfiguration() {
        fillConfigurationMappings();
    }

    protected abstract void fillConfigurationMappings();

    protected void addViewMapping(Integer GUIComponentId, ConfigurationWidgetParameter parameter) {
        widgetMap.put(GUIComponentId, parameter);
    }

    public Class<? extends AbstractWidget> getWidgetClass(Integer GUIComponentId) {
        ConfigurationWidgetParameter parameter = widgetMap.get(GUIComponentId);
        if (parameter != null) {
            return parameter.getWidgetClass();
        }

        throw new RuntimeException("No widget class found for GUIComponentId [" + GUIComponentId + "]");
    }

    public Class<? extends AbstractWidgetListener> getListenerClass(Integer GUIComponentId) {
        ConfigurationWidgetParameter parameter = widgetMap.get(GUIComponentId);
        if (parameter != null) {
            return parameter.getListenerClass();
        }

        throw new RuntimeException("No listener class found for GUIComponentId [" + GUIComponentId + "]");
    }

    public String[] getI18NParameters(Integer GUIComponentId) {
        ConfigurationWidgetParameter parameter = widgetMap.get(GUIComponentId);
        if (parameter != null) {
            return parameter.getI18nValues();
        }

        throw new RuntimeException("No i18n parameters found for GUIComponentId [" + GUIComponentId + "]");
    }
}
