package sfgamedataeditor.common;

import org.apache.log4j.Logger;
import sfgamedataeditor.common.cache.WidgetsCache;
import sfgamedataeditor.common.cache.WidgetsCachesHolder;
import sfgamedataeditor.common.viewconfigurations.AbstractConfiguration;
import sfgamedataeditor.common.viewconfigurations.AbstractConfigurationHolder;
import sfgamedataeditor.common.viewconfigurations.ConfigurationsHolder;
import sfgamedataeditor.common.widgets.AbstractWidget;
import sfgamedataeditor.common.widgets.Disabled;
import sfgamedataeditor.fieldwrapping.AbstractFieldListener;
import sfgamedataeditor.mvc.objects.Model;
import sfgamedataeditor.mvc.objects.View;

import javax.swing.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public enum ViewConfigurator {
    INSTANCE;

    private static final Logger LOGGER = Logger.getLogger(ViewConfigurator.class);

    public <T extends View> void updateViewConfiguration(T view, Model model) {
        AbstractConfigurationHolder configurationHolder = ConfigurationsHolder.INSTANCE.getConfigurationHolder(view.getClass());
        if (configurationHolder == null) {
            return;
        }

        AbstractConfiguration configuration = configurationHolder.getConfiguration(model);
        if (configuration == null) {
            return;
        }

        if (configuration.equals(configurationHolder.getCurrentConfiguration())) {
            return;
        }

        configurationHolder.setCurrentConfiguration(configuration);

        WidgetsCache widgetsCache = WidgetsCachesHolder.INSTANCE.getWidgetsCache(view.getClass());

        Field[] declaredFields = view.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            GUIElement annotation = field.getAnnotation(GUIElement.class);
            if (annotation == null) {
                continue;
            }

            int guiElementId = annotation.GUIElementId();
            Class<? extends AbstractWidget> widgetClass = configuration.getWidgetClass(guiElementId);
            Class<? extends AbstractFieldListener> listenerClass = configuration.getListenerClass(guiElementId);
            boolean inCache = widgetsCache.isWidgetExistsInCache(guiElementId, widgetClass);
            try {
                field.setAccessible(true);
                JPanel layoutPanel = (JPanel) field.get(view);
                layoutPanel.removeAll();
                AbstractWidget widget;
                AbstractFieldListener listener;
                if (inCache) {
                    widget = widgetsCache.getWidget(guiElementId, widgetClass);
                } else {
                    String[] dtoColumnNames = annotation.DTOColumnNames();
                    Field[] DTOFields = new Field[dtoColumnNames.length];
                    if (dtoColumnNames.length != 0) {
                        for (int i = 0; i < dtoColumnNames.length; ++i) {
                            DTOFields[i] = annotation.DTOClass().getDeclaredField(dtoColumnNames[i]);
                        }
                    } else {
                        // if widget component doesn't bind to ANY DTO field
                        DTOFields = null;
                    }

                    widget = widgetClass.getConstructor().newInstance();
                    listener = (AbstractFieldListener) listenerClass.getConstructors()[0].newInstance(widget, DTOFields);
                    widget.attachListener(listener);
                    Disabled disabled = field.getAnnotation(Disabled.class);
                    if (disabled != null) {
                        widget.setEnabled(false);
                    }

                    widgetsCache.addWidgetListenerPair(guiElementId, widget, listener);
                }

                layoutPanel.add(widget);
            } catch (IllegalAccessException | NoSuchMethodException | NoSuchFieldException | InstantiationException | InvocationTargetException e) {
                LOGGER.error(e.getMessage(), e);
                throw new RuntimeException("Impossible to instantiate widget instance from class [" + widgetClass.getName() + "]");
            }
        }
    }
}
