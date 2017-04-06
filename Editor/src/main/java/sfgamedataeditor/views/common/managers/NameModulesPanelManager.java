package sfgamedataeditor.views.common.managers;

import sfgamedataeditor.views.common.AbstractGhostTextListener;
import sfgamedataeditor.views.common.SubViewPanel;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class NameModulesPanelManager extends AbstractModulePanelManager {
    private static final int DEFAULT_NUMBER_OF_COLUMNS = 2;
    private static final char SPECIAL_CHARACTER = '!';
    private static final String FILTERING_PHRASE = I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "filterPanel.text");

    private static final int FILTERING_FIELD_WIDTH = 300;
    private static final int FILTERING_FIELD_HEIGHT = 25;

    private final JTextField filteringField;
    private List<SubViewPanel> subViewPanels;

    public NameModulesPanelManager() {
        filteringField = new JTextField();
        new GhostText(filteringField, FILTERING_PHRASE);
        Dimension size = new Dimension(FILTERING_FIELD_WIDTH, FILTERING_FIELD_HEIGHT);
        filteringField.setPreferredSize(size);
        filteringField.setMaximumSize(size);
        filteringField.setMinimumSize(size);
    }

    @Override
    public void updatePanelsLayout(List<SubViewPanel> subViewsPanels) {
        this.subViewPanels = subViewsPanels;
        JPanel panel = getPanel();
        panel.removeAll();

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridheight = 1;
        constraints.weighty = 1;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.insets = new Insets(5, 5, 0, 0);
        constraints.fill = GridBagConstraints.CENTER;
        panel.add(filteringField, constraints);

        int numberOfColumns = DEFAULT_NUMBER_OF_COLUMNS;

        int gridX = 0;
        int gridY = 1;
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


            if (!filteringField.getText().isEmpty()
                    && !filteringField.getText().equals(FILTERING_PHRASE)
                    && !button.getText().toLowerCase().contains(filteringField.getText().toLowerCase())) {
                continue;
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

    private void updatePanelsLayout() {
        if (subViewPanels != null && !subViewPanels.isEmpty()) {
            updatePanelsLayout(subViewPanels);
        }
    }


    private class GhostText extends AbstractGhostTextListener {

        GhostText(JTextField textfield, String ghostText) {
            super(textfield, ghostText);
        }

        @Override
        protected void invokeUpdateStateActions() {
            NameModulesPanelManager.this.updatePanelsLayout();
            NameModulesPanelManager.this.getPanel().revalidate();
            NameModulesPanelManager.this.getPanel().repaint();
        }
    }
}
