package sfgamedataeditor.common.widgets.creatures.races;

import sfgamedataeditor.common.widgets.AbstractWidget;
import sfgamedataeditor.database.creatures.parameters.CreatureParametersTableService;
import sfgamedataeditor.views.common.ObjectTuple;

import javax.swing.*;
import java.util.List;

@SuppressWarnings("unused")
public class RacesWidget extends AbstractWidget<RacesWidgetListener> {
    private JPanel mainPanel;
    private JLabel titleLabel;
    private JComboBox<ObjectTuple> racesComboBox;

    public RacesWidget() {
        fillWithPredefinedRaces();
        add(getMainPanel());
    }

    private void fillWithPredefinedRaces() {
        List<ObjectTuple> creatureRaces = CreatureParametersTableService.INSTANCE.getListOfCreatureRaces();
        for (ObjectTuple creatureRace : creatureRaces) {
            racesComboBox.addItem(creatureRace);
        }
    }

    public JComboBox<ObjectTuple> getRacesComboBox() {
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
