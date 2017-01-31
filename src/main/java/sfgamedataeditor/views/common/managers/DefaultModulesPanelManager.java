package sfgamedataeditor.views.common.managers;

import sfgamedataeditor.mvc.objects.View;
import sfgamedataeditor.views.common.SubViewPanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DefaultModulesPanelManager implements View {
    private final JPanel panel;
    private final GridLayout layout;

    public DefaultModulesPanelManager() {
        panel = new JPanel();
        layout = new GridLayout();
        panel.setLayout(layout);
    }

    public void updatePanelsLayout(List<SubViewPanel> subViewsPanels) {
        panel.removeAll();

        layout.setColumns(3);
        layout.setRows(subViewsPanels.size() / layout.getColumns());
        for (SubViewPanel subViewsPanel : subViewsPanels) {
            panel.add(subViewsPanel.getButton());
        }
    }

    @Override
    public JPanel getMainPanel() {
        return panel;
    }
}
