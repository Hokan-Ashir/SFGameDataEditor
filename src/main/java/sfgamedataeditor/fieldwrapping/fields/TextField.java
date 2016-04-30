package sfgamedataeditor.fieldwrapping.fields;

import sfgamedataeditor.listeners.TextFieldListener;

import javax.swing.*;

public class TextField extends AbstractDataField<JTextField> {

    private final TextFieldListener listener;

    public TextField(JTextField component, long fieldOffset, int lengthInBytes) {
        super(component, fieldOffset, lengthInBytes);
        listener = new TextFieldListener(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected int getFieldValue() {
        return Integer.parseInt(getComponent().getText());
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
}
