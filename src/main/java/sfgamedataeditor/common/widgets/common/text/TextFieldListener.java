package sfgamedataeditor.common.widgets.common.text;

import sfgamedataeditor.database.objects.Data;
import sfgamedataeditor.database.objects.OffsetableObject;
import sfgamedataeditor.fieldwrapping.AbstractFieldListener;
import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.utils.Notification;
import sfgamedataeditor.utils.NotificationType;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.lang.reflect.Field;

public class TextFieldListener extends AbstractFieldListener<TextFieldWidget, OffsetableObject> implements DocumentListener {


    public TextFieldListener(TextFieldWidget widget, Field[] mappedField) {
        super(widget, mappedField);
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
        String text = getWidget().getField().getText();
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

    private double getFieldMaximumValue() {
        Data annotation = getMappedFields()[0].getAnnotation(Data.class);
        int lengthInBytes = annotation.length();
        return Math.pow(2.0, 8 * lengthInBytes) - 1;
    }

    @Override
    protected int[] getFieldValues() {
        return new int[]{Integer.parseInt(getWidget().getField().getText())};
    }

    @Override
    protected void setFieldValues(int[] value) {
        final JTextField component = getWidget().getField();
        component.getDocument().removeDocumentListener(this);
        component.setText(String.valueOf(value[0]));
        component.getDocument().addDocumentListener(this);
    }
}
