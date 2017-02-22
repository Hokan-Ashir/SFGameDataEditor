package sfgamedataeditor.common.widgets.units.resource;

import sfgamedataeditor.common.widgets.AbstractWidgetListener;
import sfgamedataeditor.database.common.Data;
import sfgamedataeditor.database.common.OffsetableObject;
import sfgamedataeditor.views.utility.SilentComboBoxValuesSetter;
import sfgamedataeditor.views.utility.ViewTools;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;
import sfgamedataeditor.views.utility.notification.Notification;
import sfgamedataeditor.views.utility.notification.NotificationType;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.Field;

public class ResourceWidgetListener extends AbstractWidgetListener<ResourceWidget, OffsetableObject> implements DocumentListener, ItemListener {

    public ResourceWidgetListener(ResourceWidget component, Field... mappedFields) {
        super(component, mappedFields);
    }

    @Override
    protected int[] getFieldValues() {
        String resourceName = (String) getWidget().getResourceTypeComboBox().getSelectedItem();
        int resourceType = getWidget().getResourceIdByName(resourceName);
        int resourceAmount = Integer.parseInt(getWidget().getResourceAmountTextField().getText());
        return new int[]{resourceType, resourceAmount};
    }

    @Override
    protected void setFieldValues(int[] value) {
        int resourceType = value[0];
        int resourceAmount = value[1];

        setResourceType(resourceType);
        setResourceAmount(resourceAmount);
    }

    private void setResourceType(int resourceId) {
        final JComboBox<String> resourceTypeComboBox = getWidget().getResourceTypeComboBox();
        final String resourceName = getWidget().getResourceTypeNameByResourceId(resourceId);
        ViewTools.setComboBoxValuesSilently(new SilentComboBoxValuesSetter<String>(resourceTypeComboBox) {
            @Override
            protected void setValues() {
                resourceTypeComboBox.setSelectedItem(resourceName);
            }
        });
    }

    private void setResourceAmount(int resourceAmount) {
        final JTextField component = getWidget().getResourceAmountTextField();
        component.getDocument().removeDocumentListener(this);
        component.setText(String.valueOf(resourceAmount));
        component.getDocument().addDocumentListener(this);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() != ItemEvent.SELECTED) {
            return;
        }

        setWidgetValueToDTOField();
    }

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

    private void changeValue() {
        int value;
        String text = getWidget().getResourceAmountTextField().getText();
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
                new Notification(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "error.exceeds.max.value") + String.valueOf(maximumValue), NotificationType.ERROR);
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
}
