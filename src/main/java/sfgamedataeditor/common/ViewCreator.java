package sfgamedataeditor.common;

import org.apache.log4j.Logger;
import sfgamedataeditor.common.cache.WidgetsCache;
import sfgamedataeditor.common.cache.WidgetsCachesHolder;
import sfgamedataeditor.common.viewconfigurations.AbstractConfiguration;
import sfgamedataeditor.common.viewconfigurations.AbstractConfigurationHolder;
import sfgamedataeditor.common.viewconfigurations.ConfigurationsHolder;
import sfgamedataeditor.common.widgets.AbstractWidget;
import sfgamedataeditor.common.widgets.Disabled;
import sfgamedataeditor.mvc.objects.Model;
import sfgamedataeditor.mvc.objects.View;

import javax.swing.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public enum ViewCreator {
    INSTANCE;

    private static final Logger LOGGER = Logger.getLogger(ViewCreator.class);

    public View createView(Class<? extends View> viewClass, Model model) {
        View view;
        try {
            view = viewClass.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException("Impossible to instantiate view instance from class [" + viewClass.getName() + "]");
        }

        AbstractConfigurationHolder configurationHolder = ConfigurationsHolder.INSTANCE.getConfigurationHolder(viewClass);
        if (configurationHolder == null) {
            return view;
        }

        AbstractConfiguration configuration = configurationHolder.getConfiguration(model);
        if (configuration == null) {
            return view;
        }

        WidgetsCache widgetsCache = WidgetsCachesHolder.INSTANCE.getWidgetsCache(viewClass);

        Field[] declaredFields = viewClass.getDeclaredFields();
        for (Field field : declaredFields) {
            GUIElement annotation = field.getAnnotation(GUIElement.class);
            if (annotation == null) {
                continue;
            }

            int guiElementId = annotation.GUIElementId();
            Class<? extends AbstractWidget> widgetClass = configuration.getWidgetClass(guiElementId);
            boolean inCache = widgetsCache.isWidgetExistsInCache(guiElementId, widgetClass);
            try {
                field.setAccessible(true);
                JPanel layoutPanel = (JPanel) field.get(view);
                AbstractWidget widget;
                if (inCache) {
                    widget = widgetsCache.getWidget(guiElementId, widgetClass);
                } else {
                    String dtoColumnName = annotation.DTOColumnName();
                    Field DTOField;
                    // if widget component doesn't bind to ANY DTO field
                    if (!dtoColumnName.isEmpty()) {
                        DTOField = annotation.DTOClass().getField(dtoColumnName);
                    } else {
                        DTOField = null;
                    }

                    widget = widgetClass.getConstructor(Field.class).newInstance(DTOField);
                    Disabled disabled = field.getAnnotation(Disabled.class);
                    if (disabled != null) {
                        widget.setEnabled(false);
                    }

                    widgetsCache.addWidget(guiElementId, widget);
                }

                layoutPanel.add(widget);
            } catch (IllegalAccessException | NoSuchMethodException | NoSuchFieldException | InstantiationException | InvocationTargetException e) {
                LOGGER.error(e.getMessage(), e);
                throw new RuntimeException("Impossible to instantiate widget instance from class [" + widgetClass.getName() + "]");
            }
        }

        return view;
    }
}
