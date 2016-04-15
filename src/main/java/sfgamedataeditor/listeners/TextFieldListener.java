package sfgamedataeditor.listeners;

import sfgamedataeditor.dataextraction.DataSavingUtils;
import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.utils.Notification;
import sfgamedataeditor.utils.NotificationType;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class TextFieldListener implements DocumentListener {

    private JTextField field;
    private long offset;

    public TextFieldListener(JTextField field) {
        this.field = field;
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
        String text = field.getText();
        if (text.isEmpty()) {
            return;
        }

        try {
            value = Integer.parseInt(text);
            if (value < 0) {
                new Notification(I18N.INSTANCE.getMessage("errorNumberLessThanZero"), NotificationType.ERROR);
                return;
            }
        } catch (NumberFormatException e) {
            new Notification(I18N.INSTANCE.getMessage("errorNotANumber"), NotificationType.ERROR);
            return;
        }

        DataSavingUtils.saveDataInFile(offset, value);
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }
}
