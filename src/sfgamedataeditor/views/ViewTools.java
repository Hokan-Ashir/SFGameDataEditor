package sfgamedataeditor.views;

import javax.swing.*;
import java.awt.*;

public class ViewTools {

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
        button.setPreferredSize(new Dimension(button.getText().length() * fontSize,
                button.getPreferredSize().height));

        frame.pack();
        panel.paintImmediately(panel.getBounds());
    }
}
