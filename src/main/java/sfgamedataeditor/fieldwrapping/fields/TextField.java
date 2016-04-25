package sfgamedataeditor.fieldwrapping.fields;

import sfgamedataeditor.fieldwrapping.fields.AbstractDataField;
import sfgamedataeditor.listeners.TextFieldListener;
import sfgamedataeditor.utils.EntityTuple;

import javax.swing.*;

public class TextField extends AbstractDataField<JTextField> {

    private final TextFieldListener listener;

    public TextField(EntityTuple<JTextField> tuple) {
        super(tuple);
        listener = new TextFieldListener(getComponent());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setFieldValue(int value) {
        final JTextField component = getComponent();
        component.getDocument().removeDocumentListener(listener);
        component.setText(String.valueOf(value));
        component.getDocument().addDocumentListener(listener);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected int getFieldValue() {
        return Integer.parseInt(getComponent().getText());
    }
}
