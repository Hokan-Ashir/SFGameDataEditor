package sfgamedataeditor.common.viewconfigurations;

import sfgamedataeditor.common.widgets.AbstractWidget;
import sfgamedataeditor.common.widgets.AbstractWidgetListener;
import sfgamedataeditor.views.utility.ViewTools;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

public class ConfigurationWidgetParameter {
    private final Class<? extends AbstractWidget> widgetClass;
    private final Class<? extends AbstractWidgetListener> listenerClass;
    private final String[] i18nValues;

    public ConfigurationWidgetParameter(Class<? extends AbstractWidget> widgetClass, Class<? extends AbstractWidgetListener> listenerClass, I18NTypes type, String... i18nKeys) {
        this.widgetClass = widgetClass;
        this.listenerClass = listenerClass;

        i18nValues = new String[i18nKeys.length];
        for (int i = 0; i < i18nKeys.length; ++i) {
            i18nValues[i] = ViewTools.convertToMultiline(I18NService.INSTANCE.getMessage(type, i18nKeys[i]));
        }
    }

    public Class<? extends AbstractWidget> getWidgetClass() {
        return widgetClass;
    }

    public Class<? extends AbstractWidgetListener> getListenerClass() {
        return listenerClass;
    }

    public String[] getI18nValues() {
        return i18nValues;
    }
}
