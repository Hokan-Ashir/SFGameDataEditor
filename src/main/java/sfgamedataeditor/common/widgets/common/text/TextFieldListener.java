package sfgamedataeditor.common.widgets.common.text;

import sfgamedataeditor.fieldwrapping.AbstractFieldListener;
import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.utils.Notification;
import sfgamedataeditor.utils.NotificationType;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.lang.reflect.Field;

public class TextFieldListener extends AbstractFieldListener<JTextField> implements DocumentListener {


    public TextFieldListener(JTextField component, Field mappedField) {
        super(component, mappedField);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insertUpdate(DocumentEvent e) {
        changeValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeUpdate(DocumentEvent e) {
        changeValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void changedUpdate(DocumentEvent e) {
        changeValue();
    }

    private void changeValue() {
        int value;
        String text = getComponent().getText();
        if (text.isEmpty()) {
            return;
        }

        try {
            value = Integer.parseInt(text);
            if (value < 0) {
                new Notification(I18N.INSTANCE.getMessage("errorNumberLessThanZero"), NotificationType.ERROR);
                return;
            }

            double maximumValue = getFieldMaximumValue();
            if (value > maximumValue) {
                new Notification(I18N.INSTANCE.getMessage("error.exceeds.max.value") + String.valueOf(maximumValue), NotificationType.ERROR);
                return;
            }

        } catch (NumberFormatException e) {
            new Notification(I18N.INSTANCE.getMessage("errorNotANumber"), NotificationType.ERROR);
            return;
        }

        setValueToField();
    }

    @Override
    protected int getFieldValue() {
        return Integer.parseInt(getComponent().getText());
    }

    @Override
    protected void setFieldValue(int value) {
        final JTextField component = getComponent();
        component.getDocument().removeDocumentListener(this);
        component.setText(String.valueOf(value));
        component.getDocument().addDocumentListener(this);
    }
}
