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

//    @Override
//    public void setPreferredSize(Dimension preferredSize) {
//        super.setPreferredSize(preferredSize);
//        mainPanel.setPreferredSize(preferredSize);
//
//        comboBox.setPreferredSize(new Dimension(preferredSize.width, preferredSize.height / 2 - 5));
//        label.setPreferredSize(new Dimension(preferredSize.width, preferredSize.height / 2 - 5));
//    }
//
//    @Override
//    public void setMinimumSize(Dimension minimumSize) {
//        super.setMinimumSize(minimumSize);
//        mainPanel.setMinimumSize(minimumSize);
//
//        comboBox.setMinimumSize(new Dimension(minimumSize.width, minimumSize.height / 2 - 5));
//        label.setMinimumSize(new Dimension(minimumSize.width, minimumSize.height / 2 - 5));
//    }
//
//    @Override
//    public void setMaximumSize(Dimension maximumSize) {
//        super.setMaximumSize(maximumSize);
//        mainPanel.setMaximumSize(maximumSize);
//
//        comboBox.setMaximumSize(new Dimension(maximumSize.width, maximumSize.height / 2 - 5));
//        label.setMaximumSize(new Dimension(maximumSize.width, maximumSize.height / 2 - 5));
//    }

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
