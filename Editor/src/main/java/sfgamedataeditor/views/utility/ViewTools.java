package sfgamedataeditor.views.utility;

import sfgamedataeditor.views.common.ObjectTuple;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;
import java.util.ResourceBundle;

public class ViewTools {

    private static final int LABEL_LINE_MAX_LENGTH = 30;

    private ViewTools() {

    }

    public static void setComponentsEnableStatus(JPanel panel, boolean isEnabled) {
        setComponentsEnableStatusWithoutRepaint(panel, isEnabled);
        panel.paintImmediately(panel.getBounds());
    }

    private static void setComponentsEnableStatusWithoutRepaint(JPanel panel, boolean isEnabled) {
        for (Component component : panel.getComponents()) {
            if (component instanceof JPanel) {
                setComponentsEnableStatus((JPanel) component, isEnabled);
            }

            component.setEnabled(isEnabled);
        }
    }

    public static String convertToMultiline(String value) {
        String[] subStrings = value.split(" ");
        StringBuilder result = new StringBuilder("<html>");
        int lastNewLineInjectionPosition = 0;
        for (int i = 0; i < subStrings.length; ++i) {
            result.append(subStrings[i]).append(" ");
            if (result.length() - lastNewLineInjectionPosition > LABEL_LINE_MAX_LENGTH
                    && i != subStrings.length - 1) {
                result.append("<br>");
                lastNewLineInjectionPosition = result.length();
            }
        }
        return result.toString();
    }

    public static void setComboBoxValuesSilently(SilentComboBoxValuesSetter setter) {
        setter.setValuesSilently();
    }

    public static void replaceComboBoxContentSilently(final JComboBox<ObjectTuple> comboBox, final Collection<ObjectTuple> values) {
        SilentComboBoxValuesSetter<ObjectTuple> setter = new SilentComboBoxValuesSetter<ObjectTuple>(comboBox) {
            @Override
            protected void setValues() {
                comboBox.setSelectedItem(null);
                comboBox.removeAllItems();
                for (ObjectTuple value : values) {
                    comboBox.addItem(value);
                }
            }
        };

        setter.setValues();
    }

    public static Integer getKeyByPropertyValue(String value, I18NTypes type) {
        return Integer.parseInt(getKeyStringByPropertyValue(value, type));
    }

    public static String getKeyStringByPropertyValue(String value, I18NTypes type) {
        String result = null;
        ResourceBundle bundle = I18NService.INSTANCE.getBundle(type);
        for (String key : bundle.keySet()) {
            if (bundle.getString(key).equals(value)) {
                result = key;
                break;
            }
        }

        return result;
    }

    public static void setFirstActiveTab(JTabbedPane tabbedPane) {
        int tabCount = tabbedPane.getTabCount();
        for (int i = 0; i < tabCount; i++) {
            if (tabbedPane.isEnabledAt(i)) {
                tabbedPane.setSelectedIndex(i);
                break;
            }
        }
    }
}
