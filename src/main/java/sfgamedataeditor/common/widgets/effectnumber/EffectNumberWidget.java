package sfgamedataeditor.common.widgets.effectnumber;

import sfgamedataeditor.common.widgets.AbstractWidget;
import sfgamedataeditor.database.tableservices.SpellNameTableService;

import javax.swing.*;
import java.util.List;

public class EffectNumberWidget extends AbstractWidget<EffectNumberListener> {

    private JPanel mainPanel;
    private JComboBox<String> spellNameComboBox;
    private JComboBox<String> spellLevelComboBox;
    private JLabel spellNameLabel;
    private JLabel spellLevelLabel;
    private JButton goToEffectParametersButton;

    public EffectNumberWidget() {
        // TODO fix later
        goToEffectParametersButton.setEnabled(false);
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
    protected void insertListener(EffectNumberListener listener) {
        spellNameComboBox.addItemListener(listener);
    }

    @Override
    public void updateI18N() {

    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }
}
