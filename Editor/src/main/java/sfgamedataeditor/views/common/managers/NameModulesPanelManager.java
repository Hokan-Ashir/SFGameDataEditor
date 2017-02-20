package sfgamedataeditor.views.common.managers;

import sfgamedataeditor.views.common.SubViewPanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class NameModulesPanelManager implements ModulePanelManager {
    private static final int VERTICAL_SCROLL_UNIT_INCREMENT = 16;
    private static final int DEFAULT_NUMBER_OF_COLUMNS = 3;
    private static final char SPECIAL_CHARACTER = '!';
    private final JPanel mainPanel;
    private final JPanel panel;

    public NameModulesPanelManager() {
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

    @Override
    public void updatePanelsLayout(List<SubViewPanel> subViewsPanels) {
        panel.removeAll();

        int numberOfColumns = DEFAULT_NUMBER_OF_COLUMNS;
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.BOTH;

        int gridX = 0;
        int gridY = 0;
        char firstLetter = SPECIAL_CHARACTER;
        for (SubViewPanel subViewsPanel : subViewsPanels) {
            JButton button = subViewsPanel.getButton();
            if (!button.isVisible()) {
                continue;
            }

            char letter = button.getText().charAt(0);
            if (letter != firstLetter) {
                gridY++;
                constraints.gridx = 0;
                constraints.gridy = gridY;
                constraints.weightx = 1;
                constraints.insets = new Insets(5, 5, 0, 0);
                Label letterLabel = new Label(String.valueOf(letter));
                panel.add(letterLabel, constraints);
                firstLetter = letter;
                gridY++;
                gridX = 0;
            }

            constraints.gridx = gridX;
            constraints.gridy = gridY;
            constraints.weightx = 1.0 / numberOfColumns;
            panel.add(button, constraints);
            gridX++;
            if (gridX > numberOfColumns) {
                gridX = 0;
                gridY++;
            }
        }
    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }
}
