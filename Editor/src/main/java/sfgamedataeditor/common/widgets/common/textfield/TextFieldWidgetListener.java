package sfgamedataeditor.common.widgets.common.textfield;

import sfgamedataeditor.common.widgets.AbstractWidgetListener;
import sfgamedataeditor.database.common.Data;
import sfgamedataeditor.database.common.OffsetableObject;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;
import sfgamedataeditor.views.utility.notification.Notification;
import sfgamedataeditor.views.utility.notification.NotificationType;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.lang.reflect.Field;

public class TextFieldWidgetListener extends AbstractWidgetListener<TextFieldWidget, OffsetableObject> implements DocumentListener {


    public TextFieldWidgetListener(TextFieldWidget widget, Field[] mappedField) {
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
                new Notification(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "errorNumberLessThanZero"), NotificationType.ERROR);
                return;
            }

            double maximumValue = getFieldMaximumValue();
            if (value > maximumValue) {
                new Notification(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "error.exceeds.max.value")
                        + ": [" + String.valueOf(maximumValue) + "]", NotificationType.ERROR);
                return;
            }

        } catch (NumberFormatException e) {
            new Notification(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "errorNotANumber"), NotificationType.ERROR);
            return;
        }

        setWidgetValueToDTOField();
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
