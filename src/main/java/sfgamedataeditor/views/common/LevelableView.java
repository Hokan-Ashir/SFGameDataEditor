package sfgamedataeditor.views.common;

import sfgamedataeditor.utils.I18N;

import javax.swing.*;

public class LevelableView<T extends AbstractView> extends AbstractView<T> {
    private JPanel mainPanel;
    private JComboBox<String> levelComboBox;
    private JLabel levelLabel;

    public LevelableView(T parentView) {
        super(parentView);
        levelLabel.setText(I18N.getMessage("levelLabel"));
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

    @Override
    public void show() {

    }
}
