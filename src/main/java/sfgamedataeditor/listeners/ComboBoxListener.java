package sfgamedataeditor.listeners;

import sfgamedataeditor.dataextraction.DataSavingUtils;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ComboBoxListener implements ItemListener {

    private JComboBox comboBox;
    private long offset;

    public ComboBoxListener(JComboBox comboBox) {
        this.comboBox = comboBox;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() != ItemEvent.SELECTED) {
            return;
        }

        DataSavingUtils.saveDataInFile(offset, comboBox.getSelectedIndex());
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }
}
