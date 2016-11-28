package sfgamedataeditor.common.viewconfigurations;

import sfgamedataeditor.common.widgets.AbstractWidget;
import sfgamedataeditor.common.widgets.AbstractWidgetListener;
import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.utility.ViewTools;

public class ConfigurationWidgetParameter {
    private final Class<? extends AbstractWidget> widgetClass;
    private final Class<? extends AbstractWidgetListener> listenerClass;
    private final String[] i18nValues;

    public ConfigurationWidgetParameter(Class<? extends AbstractWidget> widgetClass, Class<? extends AbstractWidgetListener> listenerClass, String... i18nKeys) {
        this.widgetClass = widgetClass;
        this.listenerClass = listenerClass;

        i18nValues = new String[i18nKeys.length];
        for (int i = 0; i < i18nKeys.length; ++i) {
            i18nValues[i] = ViewTools.convertToMultiline(I18N.INSTANCE.getMessage(i18nKeys[i]));
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
