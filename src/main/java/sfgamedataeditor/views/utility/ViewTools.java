package sfgamedataeditor.views.utility;

import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;
import java.awt.*;
import java.util.ResourceBundle;

public class ViewTools {

    private static final int LABEL_LINE_MAX_LENGTH = 30;
    private static final double WIDTH_COEFFICIENT = 0.55;

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

    public static void repaintButtonTextContent(JButton button, JFrame frame, JPanel panel, String content) {
        button.setText(content);
        int fontSize = button.getFont().getSize();
        Dimension size = new Dimension(button.getText().length() * fontSize,
                button.getPreferredSize().height);
        if (size.getWidth() > panel.getWidth() * WIDTH_COEFFICIENT) {
            size.setSize(panel.getWidth(), size.getHeight());
        }
        button.setPreferredSize(size);
        button.setMaximumSize(size);
        button.setMinimumSize(size);

        frame.pack();
        panel.paintImmediately(panel.getBounds());
    }

    public static void centerFrame(JFrame frame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(screenSize.width / 2 - frame.getWidth() / 2, screenSize.height / 2 - frame.getHeight() / 2);
    }

    public static String convertToMultiline(String value) {
        String[] subStrings = value.split(" ");
        String result = "<html>";
        int lastNewLineInjectionPosition = 0;
        for (int i = 0; i < subStrings.length; ++i) {
            result = result + subStrings[i] + " ";
            if (result.length() - lastNewLineInjectionPosition > LABEL_LINE_MAX_LENGTH
                    && i != subStrings.length - 1) {
                result = result + "<br>";
                lastNewLineInjectionPosition = result.length();
            }
        }
        return result;
    }

    public static void setComboBoxValuesSilently(SilentComboBoxValuesSetter setter) {
        setter.setValuesSilently();
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
}
