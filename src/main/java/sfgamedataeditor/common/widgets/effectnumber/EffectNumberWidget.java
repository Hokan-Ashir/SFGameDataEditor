package sfgamedataeditor.common.widgets.effectnumber;

import sfgamedataeditor.common.widgets.AbstractWidget;
import sfgamedataeditor.database.tableservices.SpellNameTableService;
import sfgamedataeditor.utils.I18N;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class EffectNumberWidget extends AbstractWidget<EffectNumberListener> {

    private JPanel mainPanel;
    private JComboBox<String> spellNameComboBox;
    private JComboBox<String> spellLevelComboBox;
    private JLabel spellNameLabel;
    private JLabel spellLevelLabel;
    private JButton goToEffectParametersButton;
    private JLabel titleLabel;

    public EffectNumberWidget() {
        // TODO fix later
        spellNameLabel.setText(I18N.INSTANCE.getMessage("spellEffectNumber.spell"));
        spellLevelLabel.setText(I18N.INSTANCE.getMessage("spellEffectNumber.level"));
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

    @Override
    public void setMaximumSize(Dimension maximumSize) {
        super.setMaximumSize(maximumSize);
    }

    @Override
    public void setMinimumSize(Dimension minimumSize) {
        super.setMinimumSize(minimumSize);
    }

    @Override
    public void setPreferredSize(Dimension preferredSize) {
        super.setPreferredSize(preferredSize);
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
