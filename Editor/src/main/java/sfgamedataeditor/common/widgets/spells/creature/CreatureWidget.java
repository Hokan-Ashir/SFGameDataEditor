package sfgamedataeditor.common.widgets.spells.creature;

import sfgamedataeditor.common.widgets.AbstractWidget;
import sfgamedataeditor.database.creatures.parameters.CreatureParametersTableService;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;
import java.util.List;
import java.util.Set;

public class CreatureWidget extends AbstractWidget<CreatureWidgetListener> {
    private JPanel mainPanel;
    private JLabel titleLabel;
    private JComboBox<String> racesComboBox;
    private JLabel racesLabel;
    private JComboBox<String> creatureNameComboBox;
    private JLabel creatureNameLabel;
    private JButton goToCreatureParametersButton;

    public CreatureWidget() {
        // TODO fix later
        racesLabel.setText(I18NService.INSTANCE.getMessage(I18NTypes.SPELLS_GUI, "summoned.creature.race"));
        creatureNameLabel.setText(I18NService.INSTANCE.getMessage(I18NTypes.SPELLS_GUI, "summoned.creature.name"));
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

    public JComboBox<String> getCreatureNameComboBox() {
        return creatureNameComboBox;
    }

    @Override
    protected void insertListener(CreatureWidgetListener listener) {
        racesComboBox.addItemListener(listener);
        creatureNameComboBox.addItemListener(listener);
        goToCreatureParametersButton.addActionListener(listener);
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
