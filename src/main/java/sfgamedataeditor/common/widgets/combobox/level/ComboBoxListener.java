package sfgamedataeditor.common.widgets.combobox.level;

import sfgamedataeditor.fieldwrapping.AbstractFieldListener;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.Field;

public class ComboBoxListener extends AbstractFieldListener<JComboBox> implements ItemListener {

    public ComboBoxListener(JComboBox component, Field mappedField) {
        super(component, mappedField);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() != ItemEvent.SELECTED) {
            return;
        }

        setValueToField();
    }

    @Override
    protected int getFieldValue() {
        // TODO awkward, should be changed
        return 0;
    }

    @Override
    protected void setFieldValue(int value) {
        final JComboBox component = getComponent();
        component.removeItemListener(this);
        component.setSelectedItem(component.getItemAt(value));
        component.addItemListener(this);
    }
}
