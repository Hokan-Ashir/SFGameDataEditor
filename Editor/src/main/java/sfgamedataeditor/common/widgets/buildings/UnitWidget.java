package sfgamedataeditor.common.widgets.buildings;

import sfgamedataeditor.common.widgets.AbstractWidget;
import sfgamedataeditor.views.main.modules.units.races.UnitMapping;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;
import java.util.List;

public class UnitWidget extends AbstractWidget<UnitWidgetListener> {
    private JPanel mainPanel;
    private JLabel titleLabel;
    private JComboBox<String> racesComboBox;
    private JLabel racesLabel;
    private JSeparator separator;
    private JComboBox<String> unitComboBox;
    private JLabel unitLabel;
    private JButton goToUnitParametersButton;

    public UnitWidget() {
        // TODO fix later
        racesLabel.setText(I18NService.INSTANCE.getMessage(I18NTypes.BUILDING_GUI, "producing.unit.race"));
        unitLabel.setText(I18NService.INSTANCE.getMessage(I18NTypes.BUILDING_GUI, "producing.unit.name"));
        fillWithPredefinedRaces();
        add(getMainPanel());
    }

    private void fillWithPredefinedRaces() {
        List<String> unitRacesList = UnitMapping.INSTANCE.getUnitRacesList();
        for (String raceName : unitRacesList) {
            racesComboBox.addItem(raceName);
        }
    }

    public JComboBox<String> getRacesComboBox() {
        return racesComboBox;
    }

    public JComboBox<String> getUnitComboBox() {
        return unitComboBox;
    }

    @Override
    protected void insertListener(UnitWidgetListener listener) {
        racesComboBox.addItemListener(listener);
        unitComboBox.addItemListener(listener);
        goToUnitParametersButton.addActionListener(listener);
    }

    @Override
    public void updateI18N(List<String> i18nStrings) {
        if (i18nStrings.isEmpty()) {
            return;
        }

        titleLabel.setText(i18nStrings.get(0));
    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }
}
