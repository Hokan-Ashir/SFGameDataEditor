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
}
