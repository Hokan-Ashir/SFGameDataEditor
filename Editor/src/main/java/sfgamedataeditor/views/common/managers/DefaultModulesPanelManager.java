package sfgamedataeditor.views.common.managers;

import sfgamedataeditor.views.common.SubViewPanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DefaultModulesPanelManager extends AbstractModulePanelManager {
    private static final int DEFAULT_NUMBER_OF_COLUMNS = 3;

    @Override
    public void updatePanelsLayout(List<SubViewPanel> subViewsPanels) {
        JPanel panel = getPanel();
        panel.removeAll();

        int numberOfColumns = DEFAULT_NUMBER_OF_COLUMNS;
        GridBagConstraints constraints = new GridBagConstraints();
        int gridX = 0;
        int gridY = 0;
        for (SubViewPanel subViewsPanel : subViewsPanels) {
            JButton button = subViewsPanel.getButton();
            if (!button.isVisible()) {
                continue;
            }

            constraints.gridx = gridX;
            constraints.gridy = gridY;
            constraints.gridheight = 1;
            constraints.gridwidth = 1;
            constraints.weightx = 1.0 / numberOfColumns;
            constraints.weighty = 1;
            constraints.fill = GridBagConstraints.BOTH;
            panel.add(button, constraints);
            gridX++;
            if (gridX > numberOfColumns) {
                gridX = 0;
                gridY++;
            }
        }
    }
}
