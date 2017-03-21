package sfgamedataeditor.views.common.managers;

import sfgamedataeditor.views.common.SubViewPanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class NameModulesPanelManager extends AbstractModulePanelManager {
    private static final int DEFAULT_NUMBER_OF_COLUMNS = 2;
    private static final char SPECIAL_CHARACTER = '!';

    @Override
    public void updatePanelsLayout(List<SubViewPanel> subViewsPanels) {
        JPanel panel = getPanel();
        panel.removeAll();

        int numberOfColumns = DEFAULT_NUMBER_OF_COLUMNS;
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridheight = 1;
        constraints.weighty = 1;

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
                constraints.gridwidth = GridBagConstraints.REMAINDER;
                constraints.insets = new Insets(5, 5, 0, 0);
                constraints.fill = GridBagConstraints.CENTER;
                Label letterLabel = new Label("- - - - - " + String.valueOf(letter) + " - - - - -");
                panel.add(letterLabel, constraints);
                firstLetter = letter;
                gridY++;
                gridX = 0;
            }

            constraints.gridwidth = 1;
            constraints.fill = GridBagConstraints.BOTH;
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
}
