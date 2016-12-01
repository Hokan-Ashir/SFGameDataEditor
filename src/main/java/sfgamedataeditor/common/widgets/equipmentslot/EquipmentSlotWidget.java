package sfgamedataeditor.common.widgets.equipmentslot;

import sfgamedataeditor.common.widgets.AbstractWidget;

import javax.swing.*;
import java.util.List;

public class EquipmentSlotWidget extends AbstractWidget<EquipmentSlotWidgetListener> {
    private JPanel mainPanel;
    private JLabel equipmentSlotLabel;
    private JRadioButton allSlotsAvailableRadioButton;
    private JRadioButton handsAndRingsAvailableRadioButton;
    private JRadioButton noSlotsAvailable;
    private JSeparator separator;
    private JPanel parametersPanel;

    public EquipmentSlotWidget() {
        add(getMainPanel());
    }

    public JRadioButton getAllSlotsAvailableRadioButton() {
        return allSlotsAvailableRadioButton;
    }

    public JRadioButton getHandsAndRingsAvailableRadioButton() {
        return handsAndRingsAvailableRadioButton;
    }

    public JRadioButton getNoSlotsAvailable() {
        return noSlotsAvailable;
    }

    @Override
    protected void insertListener(EquipmentSlotWidgetListener listener) {
        allSlotsAvailableRadioButton.addItemListener(listener);
        handsAndRingsAvailableRadioButton.addItemListener(listener);
        noSlotsAvailable.addItemListener(listener);
    }

    @Override
    public void updateI18N(List<String> i18nStrings) {
        equipmentSlotLabel.setText(i18nStrings.get(0));
        allSlotsAvailableRadioButton.setText(i18nStrings.get(1));
        handsAndRingsAvailableRadioButton.setText(i18nStrings.get(2));
        noSlotsAvailable.setText(i18nStrings.get(3));
    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }
}
