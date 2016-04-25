package sfgamedataeditor.fieldwrapping.fields;

import sfgamedataeditor.fieldwrapping.fields.AbstractDataField;
import sfgamedataeditor.listeners.ComboBoxListener;
import sfgamedataeditor.utils.EntityTuple;

import javax.swing.*;

public class ComboBox extends AbstractDataField<JComboBox> {

    private final ComboBoxListener listener;

    public ComboBox(EntityTuple<JComboBox> tuple) {
        super(tuple);
        listener = new ComboBoxListener(tuple.getComponent());
        tuple.getComponent().addItemListener(listener);
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

    /**
     * {@inheritDoc}
     */
    @Override
    protected int getFieldValue() {
        return 0;
    }
}
