package sfgamedataeditor.fieldwrapping.fields;

import sfgamedataeditor.listeners.ComboBoxListener;

import javax.swing.*;
import java.lang.reflect.Field;

public class ComboBox extends AbstractDataField<JComboBox> {

    private final ComboBoxListener listener;

    public ComboBox(JComboBox component, Field mappedField) {
        super(component, mappedField);
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
