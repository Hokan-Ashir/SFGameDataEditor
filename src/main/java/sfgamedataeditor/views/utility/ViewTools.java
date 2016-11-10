package sfgamedataeditor.views.utility;

import javax.swing.*;
import java.awt.*;

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
}
