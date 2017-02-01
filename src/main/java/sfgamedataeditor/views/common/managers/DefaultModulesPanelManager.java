package sfgamedataeditor.views.common.managers;

import sfgamedataeditor.mvc.objects.View;
import sfgamedataeditor.views.common.SubViewPanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DefaultModulesPanelManager implements View {
    private static final int VERTICAL_SCROLL_UNIT_INCREMENT = 16;
    private static final int DEFAULT_NUMBER_OF_COLUMNS = 3;
    private final JPanel mainPanel;
    private final JPanel panel;
    private final GridLayout layout;

    public DefaultModulesPanelManager() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 1));
        layout = new GridLayout();
        panel = new JPanel();
        panel.setLayout(layout);
        panel.setPreferredSize(null);
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(VERTICAL_SCROLL_UNIT_INCREMENT);
        mainPanel.add(scrollPane);
    }

    public void updatePanelsLayout(List<SubViewPanel> subViewsPanels) {
        panel.removeAll();

        layout.setColumns(DEFAULT_NUMBER_OF_COLUMNS);
        layout.setRows(subViewsPanels.size() / layout.getColumns());
        for (SubViewPanel subViewsPanel : subViewsPanels) {
            panel.add(subViewsPanel.getButton());
        }
    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }
}
