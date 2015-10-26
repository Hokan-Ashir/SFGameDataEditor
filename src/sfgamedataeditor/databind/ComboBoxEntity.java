package sfgamedataeditor.databind;

import javax.swing.*;

public class ComboBoxEntity extends Entity<JComboBox> {

    public ComboBoxEntity(JComboBox component, long offsetInBytes, int dataLengthInBytes) {
        super(component, offsetInBytes, dataLengthInBytes);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setFieldValue() {
        int temp = 0;
        for (int i = 0; i < getValue().length; i++) {
            temp += getValue()[i] << ((getValue().length - 1 - i) * 8);
        }

        // in case spell has no specified category, which can be described via comboBox values - no value
        // should be selected
        if (getComponent().getItemCount() != 0) {
            // cause comboBox-item indexes enumeration begins with "0", subtract "1"
            // to match spell values enumeration which begins with "1"

            // TODO check why this causes error
            if (getComponent().getItemCount() >= temp) {
                getComponent().setSelectedIndex(temp - 1);
            }
        }
    }
}
