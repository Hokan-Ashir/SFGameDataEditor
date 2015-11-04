package sfgamedataeditor.databind.entity;

import javax.swing.*;

public class TextFieldEntity extends Entity<JTextField> {

    public TextFieldEntity(JTextField field, long offsetInBytes, int dataLengthInBytes) {
        super(field, offsetInBytes, dataLengthInBytes);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setFieldValue() {
        String fieldValue = createFieldValue();
        getComponent().setText(fieldValue);
    }

    private String createFieldValue() {
        long temp = 0;
        for (int i = 0; i < getValue().length; i++) {
            temp += getValue()[i] << ((getValue().length - 1 - i) * 8);
        }

        return String.valueOf(temp);
    }
}
