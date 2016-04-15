package sfgamedataeditor.views.common;

import sfgamedataeditor.databind.files.FilesContainer;
import sfgamedataeditor.utils.I18N;

import javax.swing.*;

public class LevelableView<T extends AbstractView> extends AbstractView<T> {
    private static final int HIGHEST_ORDER_WINTER_LEVEL = 12;
    private static final int HIGHEST_PHOENIX_LEVEL = 20;

    private JPanel mainPanel;
    private JComboBox<String> levelComboBox;
    private JLabel levelLabel;

    public LevelableView(T parentView) {
        super(parentView);
        levelLabel.setText(I18N.INSTANCE.getMessage("levelLabel"));
        fillLevelComboBox();
    }

    private void fillLevelComboBox() {
        boolean isVersion11 = FilesContainer.INSTANCE.isVersion11();
        int highestLevel;
        if (isVersion11) {
            highestLevel = HIGHEST_ORDER_WINTER_LEVEL;
        } else {
            highestLevel = HIGHEST_PHOENIX_LEVEL;
        }

        levelComboBox.removeAllItems();
        for (int i = 1; i <= highestLevel; i++) {
            levelComboBox.addItem(String.valueOf(i));
        }
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public int getSelectedLevel() {
        return Integer.valueOf((String) levelComboBox.getSelectedItem());
    }

    public JComboBox<String> getLevelComboBox() {
        return levelComboBox;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void show() {

    }
}
