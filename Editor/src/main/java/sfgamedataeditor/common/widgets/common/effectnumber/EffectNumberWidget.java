package sfgamedataeditor.common.widgets.common.effectnumber;

import sfgamedataeditor.common.widgets.AbstractWidget;
import sfgamedataeditor.database.spells.names.SpellNameTableService;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("unused")
public class EffectNumberWidget extends AbstractWidget<EffectNumberWidgetListener> {
    private JPanel mainPanel;
    private JComboBox<String> spellNameComboBox;
    private JComboBox<String> spellLevelComboBox;
    private JLabel spellNameLabel;
    private JLabel spellLevelLabel;
    private JButton goToEffectParametersButton;
    private JLabel titleLabel;

    public EffectNumberWidget() {
        spellNameLabel.setText(I18NService.INSTANCE.getMessage(I18NTypes.SPELLS_GUI, "spellEffectNumber.spell"));
        spellLevelLabel.setText(I18NService.INSTANCE.getMessage(I18NTypes.SPELLS_GUI, "spellEffectNumber.level"));
        fillSpellNameComboBoxValues();
        add(getMainPanel());
    }

    private void fillSpellNameComboBoxValues() {
        // TODO add search inside comboBox
        List<String> allSpellNames = SpellNameTableService.INSTANCE.getAllSpellNames();
        Collections.sort(allSpellNames);
        for (String allSpellName : allSpellNames) {
            spellNameComboBox.addItem(allSpellName);
        }
    }

    JComboBox<String> getSpellNameComboBox() {
        return spellNameComboBox;
    }

    JComboBox<String> getSpellLevelComboBox() {
        return spellLevelComboBox;
    }

    @Override
    public void setVisible(boolean isVisible) {
        super.setVisible(isVisible);
        spellNameComboBox.setVisible(isVisible);
        spellLevelComboBox.setVisible(isVisible);
        spellNameLabel.setVisible(isVisible);
        spellLevelLabel.setVisible(isVisible);
        goToEffectParametersButton.setVisible(isVisible);
        titleLabel.setVisible(isVisible);
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
