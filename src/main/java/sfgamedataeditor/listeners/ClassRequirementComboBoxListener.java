package sfgamedataeditor.listeners;

import sfgamedataeditor.datamapping.Mappings;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

public class ClassRequirementComboBoxListener implements ItemListener {
    private JComboBox subClassComboBox;

    public ClassRequirementComboBoxListener(JComboBox subClassComboBox) {
        this.subClassComboBox = subClassComboBox;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() != ItemEvent.SELECTED) {
            return;
        }

        List<String> subClasses = Mappings.INSTANCE.CLASS_SUBCLASS_COMBOBOX_MAP.get(e.getItem());
        subClassComboBox.removeAllItems();
        for (String subClass : subClasses) {
            subClassComboBox.addItem(subClass);
        }
    }
}