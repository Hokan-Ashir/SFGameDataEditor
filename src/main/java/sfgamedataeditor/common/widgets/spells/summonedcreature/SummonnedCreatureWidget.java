package sfgamedataeditor.common.widgets.spells.summonedcreature;

import sfgamedataeditor.common.widgets.AbstractWidget;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;
import java.util.List;

public class SummonnedCreatureWidget extends AbstractWidget<SummonedCreatureWidgetListener> {
    private JPanel mainPanel;
    private JLabel titleLabel;
    private JComboBox racesComboBox;
    private JLabel racesLabel;
    private JSeparator separator;
    private JComboBox creatureNameComboBox;
    private JLabel creatureNameLabel;
    private JButton goToCreatureParametersButton;

    public SummonnedCreatureWidget() {
        // TODO fix later
        racesLabel.setText(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES_GUI, "spellEffectNumber.spell"));
        creatureNameLabel.setText(I18NService.INSTANCE.getMessage(I18NTypes.CREATURES_GUI, "spellEffectNumber.level"));
        fillRacesComboBoxValues();
        add(getMainPanel());
    }

    private void fillRacesComboBoxValues() {

    }

    @Override
    protected void insertListener(SummonedCreatureWidgetListener listener) {

    }

    @Override
    public void updateI18N(List<String> i18nStrings) {

    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }
}
