package sfgamedataeditor.views;

import javax.swing.*;
import java.awt.*;

public class ViewTools {

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
}
