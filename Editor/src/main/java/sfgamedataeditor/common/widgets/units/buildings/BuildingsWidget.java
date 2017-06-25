package sfgamedataeditor.common.widgets.units.buildings;

import sfgamedataeditor.common.widgets.AbstractWidget;
import sfgamedataeditor.database.buildings.common.BuildingsTableService;
import sfgamedataeditor.views.common.SubViewPanelTuple;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;
import java.util.List;

@SuppressWarnings("unused")
public class BuildingsWidget extends AbstractWidget<BuildingsWidgetListener> {
    private JPanel mainPanel;
    private JLabel titleLabel;
    private JLabel raceLabel;
    private JLabel buildingLabel;
    private JComboBox<SubViewPanelTuple> racesComboBox;
    private JComboBox<SubViewPanelTuple> buildingComboBox;
    private JSeparator separator;
    private JButton goToBuildingParametersButton;

    public BuildingsWidget() {
        raceLabel.setText(I18NService.INSTANCE.getMessage(I18NTypes.UNITS_GUI, "required.building.race"));
        buildingLabel.setText(I18NService.INSTANCE.getMessage(I18NTypes.UNITS_GUI, "required.building.name"));
        fillWithPredefinedRaces();
        add(getMainPanel());
    }

    private void fillWithPredefinedRaces() {
        List<SubViewPanelTuple> creatureRaces = BuildingsTableService.INSTANCE.getBuildingsRacesNames();
        for (SubViewPanelTuple tuple : creatureRaces) {
            racesComboBox.addItem(tuple);
        }
    }

    public JComboBox<SubViewPanelTuple> getRacesComboBox() {
        return racesComboBox;
    }

    JComboBox<SubViewPanelTuple> getBuildingComboBox() {
        return buildingComboBox;
    }

    @Override
    protected void insertListener(BuildingsWidgetListener listener) {
        racesComboBox.addItemListener(listener);
        buildingComboBox.addItemListener(listener);
        goToBuildingParametersButton.addActionListener(listener);
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
