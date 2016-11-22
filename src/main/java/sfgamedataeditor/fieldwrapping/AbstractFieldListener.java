package sfgamedataeditor.fieldwrapping;

import org.apache.log4j.Logger;
import sfgamedataeditor.database.objects.Data;
import sfgamedataeditor.database.objects.OffsetableObject;
import sfgamedataeditor.database.tableservices.CommonTableService;

import javax.swing.*;
import java.lang.reflect.Field;

public abstract class AbstractFieldListener<T extends JComponent> {
    private static final Logger LOGGER = Logger.getLogger(AbstractFieldListener.class);

    private Field mappedField;
    private T component;
    private OffsetableObject mappedObject;

    public AbstractFieldListener(T component, Field mappedField) {
        this.component = component;
        this.mappedField = mappedField;
    }

    /**
     * {@inheritDoc}
     */
    public void mapValues(OffsetableObject mappedObject) {
        this.mappedObject = mappedObject;
        try {
            mappedField.setAccessible(true);
            Object o = mappedField.get(mappedObject);
            setFieldValue((Integer) o);
        } catch (IllegalAccessException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void setValueToField() {
        // TODO get rid of it; this is the cause when:
        // user first time select ANY spell
        // data loading is processing through AbstractDataFields
        // the data have been set to any ClassRequirements comboBox
        // the listener (ClassRequirementComboBoxListener) on it is launched to set subClass comboBox value
        // BUT there is no mapped object on this subClass comboBox, so NPE appears
        if (mappedObject == null) {
            return;
        }
        int value = getFieldValue();
        mappedField.setAccessible(true);
        try {
            mappedField.set(mappedObject, value);
        } catch (IllegalAccessException e) {
            LOGGER.error(e.getMessage(), e);
        }

        CommonTableService.INSTANCE.updateObject(mappedObject, mappedObject.getClass());
    }

    protected abstract int getFieldValue();

    protected abstract void setFieldValue(int value);

    public double getFieldMaximumValue() {
        Data annotation = mappedField.getAnnotation(Data.class);
        int lengthInBytes = annotation.length();
        return Math.pow(2.0, 8 * lengthInBytes) - 1;
    }

    public T getComponent() {
        return component;
    }
}
