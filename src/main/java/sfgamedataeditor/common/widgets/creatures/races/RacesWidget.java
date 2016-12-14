package sfgamedataeditor.common.widgets.creatures.races;

import sfgamedataeditor.common.widgets.AbstractWidget;

import javax.swing.*;
import java.util.List;

public class RacesWidget extends AbstractWidget<RacesWidgetListener> {
    private JPanel mainPanel;
    private JLabel titleLabel;
    private JComboBox<String> racesComboBox;

    public RacesWidget() {
        add(getMainPanel());
    }

    public JComboBox<String> getRacesComboBox() {
        return racesComboBox;
    }

    @Override
    protected void insertListener(RacesWidgetListener listener) {
        racesComboBox.addItemListener(listener);
    }

    @Override
    public void updateI18N(List<String> i18nStrings) {
        titleLabel.setText(i18nStrings.get(0));
    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }
}
