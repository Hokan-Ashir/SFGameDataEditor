package sfgamedataeditor.common.widgets.creatures.races;

import sfgamedataeditor.common.widgets.AbstractWidget;
import sfgamedataeditor.database.creatures.parameters.CreatureParametersTableService;

import javax.swing.*;
import java.util.List;
import java.util.Set;

@SuppressWarnings("unused")
public class RacesWidget extends AbstractWidget<RacesWidgetListener> {
    private JPanel mainPanel;
    private JLabel titleLabel;
    private JComboBox<String> racesComboBox;

    public RacesWidget() {
        fillWithPredefinedRaces();
        add(getMainPanel());
    }

    private void fillWithPredefinedRaces() {
        Set<String> creatureRaces = CreatureParametersTableService.INSTANCE.getListOfCreatureRaces();
        for (String creatureRace : creatureRaces) {
            racesComboBox.addItem(creatureRace);
        }
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
