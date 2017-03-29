package sfgamedataeditor.common.widgets.creatures.equipmentslot;

import sfgamedataeditor.common.widgets.AbstractWidget;

import javax.swing.*;
import java.util.List;

@SuppressWarnings("unused")
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

    JRadioButton getAllSlotsAvailableRadioButton() {
        return allSlotsAvailableRadioButton;
    }

    JRadioButton getHandsAndRingsAvailableRadioButton() {
        return handsAndRingsAvailableRadioButton;
    }

    JRadioButton getNoSlotsAvailable() {
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
