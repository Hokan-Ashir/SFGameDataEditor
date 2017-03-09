package sfgamedataeditor.common;

import org.apache.log4j.Logger;
import sfgamedataeditor.common.cache.WidgetsCache;
import sfgamedataeditor.common.cache.WidgetsCachesHolder;
import sfgamedataeditor.common.viewconfigurations.AbstractConfiguration;
import sfgamedataeditor.common.viewconfigurations.AbstractConfigurationHolder;
import sfgamedataeditor.common.viewconfigurations.ConfigurationsHolder;
import sfgamedataeditor.common.widgets.AbstractWidget;
import sfgamedataeditor.common.widgets.AbstractWidgetListener;
import sfgamedataeditor.common.widgets.Disabled;
import sfgamedataeditor.mvc.objects.Model;
import sfgamedataeditor.mvc.objects.View;

import javax.swing.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

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

        WidgetsCache widgetsCache = WidgetsCachesHolder.INSTANCE.getWidgetsCache(view.getClass());

        Field[] declaredFields = view.getClass().getDeclaredFields();

        for (Field field : declaredFields) {
            GUIElement annotation = field.getAnnotation(GUIElement.class);
            if (annotation == null) {
                continue;
            }

            field.setAccessible(true);
            Object panel;
            try {
                panel = field.get(view);
            } catch (IllegalAccessException e) {
                LOGGER.error(e.getMessage(), e);
                continue;
            }

            int guiElementId = annotation.GUIElementId();
            Class<? extends AbstractWidget> widgetClass = configuration.getWidgetClass(guiElementId);
            if (widgetClass == null) {
                continue;
            }

            Class<? extends AbstractWidgetListener> listenerClass = configuration.getListenerClass(guiElementId);
            String[] i18NParameters = configuration.getI18NParameters(guiElementId);
            boolean inCache = widgetsCache.isWidgetExistsInCache(guiElementId, widgetClass);
            try {
                JPanel layoutPanel = (JPanel) panel;
                layoutPanel.removeAll();
                AbstractWidget widget;
                AbstractWidgetListener listener;
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
                    listener = (AbstractWidgetListener) listenerClass.getConstructors()[0].newInstance(widget, DTOFields);
                    widget.attachListener(listener);
                    widget.updateI18N(Arrays.asList(i18NParameters));
                    Disabled disabled = field.getAnnotation(Disabled.class);
                    if (disabled != null) {
                        widget.setEnabled(false);
                    }

                    widgetsCache.addWidgetListenerPair(guiElementId, widget, listener);
                }

                widget.setMinimumSize(layoutPanel.getMinimumSize());
                widget.setMaximumSize(layoutPanel.getMaximumSize());
                widget.setPreferredSize(layoutPanel.getPreferredSize());
                layoutPanel.add(widget);
            } catch (IllegalAccessException | NoSuchMethodException | NoSuchFieldException | InstantiationException | InvocationTargetException e) {
                LOGGER.error(e.getMessage(), e);
                throw new RuntimeException("Impossible to instantiate widget instance from class [" + widgetClass.getName() + "]");
            }
        }

        configurationHolder.setCurrentConfiguration(configuration);
    }
}
