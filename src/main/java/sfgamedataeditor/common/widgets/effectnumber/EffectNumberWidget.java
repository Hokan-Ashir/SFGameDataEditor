package sfgamedataeditor.common.widgets.effectnumber;

import sfgamedataeditor.common.widgets.AbstractWidget;

import javax.swing.*;

public class EffectNumberWidget extends AbstractWidget<EffectNumberListener> {

    private JPanel mainPanel;
    private JComboBox spellNameComboBox;
    private JComboBox spellLevelComboBox;
    private JLabel spellNameLabel;
    private JLabel spellLevelLabel;

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
