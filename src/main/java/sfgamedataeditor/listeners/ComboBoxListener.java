package sfgamedataeditor.listeners;

import sfgamedataeditor.fieldwrapping.fields.ComboBox;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ComboBoxListener implements ItemListener {

    private ComboBox comboBox;

    public ComboBoxListener(ComboBox comboBox) {
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

        comboBox.saveToFile();
    }
}
