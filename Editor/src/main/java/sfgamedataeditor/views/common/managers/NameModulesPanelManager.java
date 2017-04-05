package sfgamedataeditor.views.common.managers;

import sfgamedataeditor.views.common.SubViewPanel;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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

    // taken from http://stackoverflow.com/questions/10506789/how-to-display-faint-gray-ghost-text-in-a-jtextfield
    private class GhostText implements FocusListener, DocumentListener, PropertyChangeListener {
        private static final String FOREGROUND_PROPERTY_NAME = "foreground";
        private final JTextField textfield;
        private boolean isEmpty;
        private Color ghostColor;
        private Color foregroundColor;
        private final String ghostText;

        GhostText(final JTextField textfield, String ghostText) {
            super();
            this.textfield = textfield;
            this.ghostText = ghostText;
            this.ghostColor = Color.LIGHT_GRAY;
            textfield.addFocusListener(this);
            registerListeners();
            updateState();
            if (!this.textfield.hasFocus()) {
                focusLost(null);
            }
        }

        private void registerListeners() {
            textfield.getDocument().addDocumentListener(this);
            textfield.addPropertyChangeListener(FOREGROUND_PROPERTY_NAME, this);
        }

        private void unregisterListeners() {
            textfield.getDocument().removeDocumentListener(this);
            textfield.removePropertyChangeListener(FOREGROUND_PROPERTY_NAME, this);
        }

        private void updateState() {
            isEmpty = textfield.getText().length() == 0;
            foregroundColor = textfield.getForeground();

            NameModulesPanelManager.this.updatePanelsLayout();
            NameModulesPanelManager.this.getPanel().revalidate();
            NameModulesPanelManager.this.getPanel().repaint();
            textfield.grabFocus();
        }

        @Override
        public void focusGained(FocusEvent e) {
            if (!isEmpty) {
                return;
            }

            unregisterListeners();
            try {
                textfield.setText("");
                textfield.setForeground(foregroundColor);
            } finally {
                registerListeners();
            }
        }

        @Override
        public void focusLost(FocusEvent e) {
            if (!isEmpty) {
                return;
            }

            unregisterListeners();
            try {
                textfield.setText(ghostText);
                textfield.setForeground(ghostColor);
            } finally {
                registerListeners();
            }
        }

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            updateState();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            updateState();
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            updateState();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            updateState();
        }

    }
}
