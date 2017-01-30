package sfgamedataeditor.views.common.managers;

import sfgamedataeditor.mvc.objects.View;
import sfgamedataeditor.views.common.SubViewPanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DefaultModulesPanelManager implements View {
    private final JPanel panel;
    private final GridBagLayout layout;

    public DefaultModulesPanelManager() {
        panel = new JPanel();
        layout = new GridBagLayout();
    }

    public void updatePanelsLayout(List<SubViewPanel> subViewsPanels) {

    }

    @Override
    public JPanel getMainPanel() {
        return panel;
    }
}
