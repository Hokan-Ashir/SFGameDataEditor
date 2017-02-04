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

        int numberOfVisiblePanels = 0;
        for (SubViewPanel subViewsPanel : subViewsPanels) {
            if (subViewsPanel.getButton().isVisible()) {
                panel.add(subViewsPanel.getButton());
                numberOfVisiblePanels++;
            }
        }

        layout.setColumns(DEFAULT_NUMBER_OF_COLUMNS);
        layout.setRows(numberOfVisiblePanels / layout.getColumns());
    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }
}
