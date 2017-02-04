package sfgamedataeditor.common.viewconfigurations;

import org.apache.log4j.Logger;
import sfgamedataeditor.common.widgets.AbstractWidget;
import sfgamedataeditor.common.widgets.AbstractWidgetListener;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractConfiguration {

    private final Map<Integer, ConfigurationWidgetParameter> widgetMap = new HashMap<>();
    private static final Logger LOGGER = Logger.getLogger(AbstractConfiguration.class);

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

        LOGGER.info("No widget class found for GUIComponentId [" + GUIComponentId + "]");
        return null;
    }

    public Class<? extends AbstractWidgetListener> getListenerClass(Integer GUIComponentId) {
        ConfigurationWidgetParameter parameter = widgetMap.get(GUIComponentId);
        if (parameter != null) {
            return parameter.getListenerClass();
        }

        LOGGER.info("No listener class found for GUIComponentId [" + GUIComponentId + "]");
        return null;
    }

    public String[] getI18NParameters(Integer GUIComponentId) {
        ConfigurationWidgetParameter parameter = widgetMap.get(GUIComponentId);
        if (parameter != null) {
            return parameter.getI18nValues();
        }

        LOGGER.info("No i18n parameters found for GUIComponentId [" + GUIComponentId + "]");
        return new String[]{};
    }
}
