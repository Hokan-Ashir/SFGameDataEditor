package sfgamedataeditor.views.common.managers;

import sfgamedataeditor.mvc.objects.View;
import sfgamedataeditor.views.common.SubViewPanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public abstract class AbstractModulePanelManager implements View {
    private static final int VERTICAL_SCROLL_UNIT_INCREMENT = 16;
    private final JPanel mainPanel;
    private final JPanel panel;

    public AbstractModulePanelManager() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 1));
        GridBagLayout layout = new GridBagLayout();
        panel = new JPanel();
        panel.setLayout(layout);
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        scrollPane.getVerticalScrollBar().setUnitIncrement(VERTICAL_SCROLL_UNIT_INCREMENT);
        mainPanel.add(scrollPane);
    }

    public JPanel getPanel() {
        return panel;
    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }

    public abstract void updatePanelsLayout(List<SubViewPanel> subViewsPanels);
}
