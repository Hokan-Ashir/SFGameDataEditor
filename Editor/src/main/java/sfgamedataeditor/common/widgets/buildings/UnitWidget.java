package sfgamedataeditor.common.widgets.buildings;

import sfgamedataeditor.common.widgets.AbstractWidget;
import sfgamedataeditor.views.common.ObjectTuple;
import sfgamedataeditor.views.main.modules.units.races.UnitMapping;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;
import java.util.List;

@SuppressWarnings("unused")
public class UnitWidget extends AbstractWidget<UnitWidgetListener> {
    private JPanel mainPanel;
    private JLabel titleLabel;
    private JComboBox<ObjectTuple> racesComboBox;
    private JLabel racesLabel;
    private JSeparator separator;
    private JComboBox<ObjectTuple> unitComboBox;
    private JLabel unitLabel;
    private JButton goToUnitParametersButton;

    public UnitWidget() {
        racesLabel.setText(I18NService.INSTANCE.getMessage(I18NTypes.BUILDING_GUI, "producing.unit.race"));
        unitLabel.setText(I18NService.INSTANCE.getMessage(I18NTypes.BUILDING_GUI, "producing.unit.name"));
        fillWithPredefinedRaces();
        add(getMainPanel());
    }

    private void fillWithPredefinedRaces() {
        List<ObjectTuple> unitRacesList = UnitMapping.INSTANCE.getUnitRacesList();
        for (ObjectTuple tuple : unitRacesList) {
            racesComboBox.addItem(tuple);
        }
    }

    public JComboBox<ObjectTuple> getRacesComboBox() {
        return racesComboBox;
    }

    JComboBox<ObjectTuple> getUnitComboBox() {
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
