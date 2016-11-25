package sfgamedataeditor.common.widgets.combobox.level;

import sfgamedataeditor.common.widgets.AbstractWidget;

import javax.swing.*;
import java.util.List;

public class LevelComboBoxWidget extends AbstractWidget<LevelComboBoxListener> {
    private JPanel mainPanel;
    private JComboBox<String> comboBox;
    private JLabel label;

    public LevelComboBoxWidget() {
        add(getMainPanel());
    }

    public JComboBox<String> getComboBox() {
        return comboBox;
    }

    @Override
    public void insertListener(LevelComboBoxListener listener) {
        comboBox.addItemListener(listener);
    }

    @Override
    public void updateI18N(List<String> i18nStrings) {
        label.setText(i18nStrings.get(0));
    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }
}
