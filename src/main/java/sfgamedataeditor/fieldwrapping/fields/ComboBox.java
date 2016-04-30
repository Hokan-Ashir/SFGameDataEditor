package sfgamedataeditor.fieldwrapping.fields;

import sfgamedataeditor.listeners.ComboBoxListener;

import javax.swing.*;

public class ComboBox extends AbstractDataField<JComboBox> {

    private final ComboBoxListener listener;

    public ComboBox(JComboBox component, long fieldOffset, int lengthInBytes) {
        super(component, fieldOffset, lengthInBytes);
        JComboBox comboBox = getComponent();
        listener = new ComboBoxListener(this);
        comboBox.addItemListener(listener);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected int getFieldValue() {
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setFieldValue(int value) {
        final JComboBox component = getComponent();
        component.removeItemListener(listener);
        component.setSelectedItem(component.getItemAt(value));
        component.addItemListener(listener);
    }
}
