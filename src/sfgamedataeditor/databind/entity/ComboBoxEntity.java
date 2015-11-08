package sfgamedataeditor.databind.entity;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ComboBoxEntity extends Entity<JComboBox> {

    public ComboBoxEntity(final JComboBox component, long offsetInBytes, int dataLengthInBytes) {
        super(component, offsetInBytes, dataLengthInBytes);
        component.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() != ItemEvent.SELECTED) {
                    return;
                }

                ComboBoxEntity.this.setFieldValue(component.getSelectedIndex());
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void viewFieldValue() {
        int temp = getFieldValue();

        // in case spell has no specified category, which can be described via comboBox values - no value
        // should be selected
        if (getComponent().getItemCount() != 0) {
            // cause comboBox-item indexes enumeration begins with "0", subtract "1"
            // to match spell values enumeration which begins with "1"
            getComponent().setSelectedIndex(temp);
        }
    }
}
