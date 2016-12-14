package sfgamedataeditor.common.widgets.effectnumber;

import sfgamedataeditor.common.widgets.AbstractWidget;
import sfgamedataeditor.database.spells.names.SpellNameTableService;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;
import java.util.List;

public class EffectNumberWidget extends AbstractWidget<EffectNumberWidgetListener> {

    private JPanel mainPanel;
    private JComboBox<String> spellNameComboBox;
    private JComboBox<String> spellLevelComboBox;
    private JLabel spellNameLabel;
    private JLabel spellLevelLabel;
    private JButton goToEffectParametersButton;
    private JLabel titleLabel;

    public EffectNumberWidget() {
        // TODO fix later
        spellNameLabel.setText(I18NService.INSTANCE.getMessage(I18NTypes.SPELLS_GUI, "spellEffectNumber.spell"));
        spellLevelLabel.setText(I18NService.INSTANCE.getMessage(I18NTypes.SPELLS_GUI, "spellEffectNumber.level"));
        fillSpellNameComboBoxValues();
        add(getMainPanel());
    }

    private void fillSpellNameComboBoxValues() {
        // TODO add search inside comboBox and sorting in alphabetical order
        List<String> allSpellNames = SpellNameTableService.INSTANCE.getAllSpellNames();
        for (String allSpellName : allSpellNames) {
            spellNameComboBox.addItem(allSpellName);
        }
    }

    public JComboBox<String> getSpellNameComboBox() {
        return spellNameComboBox;
    }

    public JComboBox<String> getSpellLevelComboBox() {
        return spellLevelComboBox;
    }

    @Override
    protected void insertListener(EffectNumberWidgetListener listener) {
        spellNameComboBox.addItemListener(listener);
        spellLevelComboBox.addItemListener(listener);
        goToEffectParametersButton.addActionListener(listener);
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
