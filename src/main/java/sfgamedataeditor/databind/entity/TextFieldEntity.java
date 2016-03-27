package sfgamedataeditor.databind.entity;

import sfgamedataeditor.I18N;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class TextFieldEntity extends Entity<JTextField> {

    public TextFieldEntity(final JTextField field, long offsetInBytes, int dataLengthInBytes) {
        super(field, offsetInBytes, dataLengthInBytes);
        field.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                changeValue();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                changeValue();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                changeValue();
            }

            public void changeValue() {
                int value;
                String text = field.getText();
                if (text.isEmpty()) {
                    return;
                }

                try {
                    value = Integer.parseInt(text);
                    if (value < 0) {
                        JOptionPane.showMessageDialog(null,
                                I18N.getMessage("errorNumberLessThanZero"), I18N.getMessage("error"),
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null,
                            I18N.getMessage("errorNotANumber"), I18N.getMessage("error"),
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                TextFieldEntity.this.setFieldValue(value);
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void viewFieldValue() {
        String fieldValue = createFieldValue();
        getComponent().setText(fieldValue);
    }

    private String createFieldValue() {
        long temp = getFieldValue();
        return String.valueOf(temp);
    }
}
