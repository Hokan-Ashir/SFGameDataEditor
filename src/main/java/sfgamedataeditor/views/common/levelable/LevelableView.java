package sfgamedataeditor.views.common.levelable;

import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.common.AbstractView;

import javax.swing.*;

public class LevelableView<T extends AbstractView> extends AbstractView<T> {
    private JPanel mainPanel;
    private JComboBox<String> levelComboBox;
    private JLabel levelLabel;

    public LevelableView(T parentView) {
        super(parentView);
        levelLabel.setText(I18N.INSTANCE.getMessage("levelLabel"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }

    public Integer getSelectedLevel() {
        return Integer.valueOf((String) levelComboBox.getSelectedItem());
    }

    public JComboBox<String> getLevelComboBox() {
        return levelComboBox;
    }
}
