package sfgamedataeditor.fieldwrapping.fields;

import org.apache.log4j.Logger;
import sfgamedataeditor.database.TableCreationUtils;
import sfgamedataeditor.database.objects.OffsetableObject;
import sfgamedataeditor.databind.files.FilesContainer;
import sfgamedataeditor.fieldwrapping.Data;

import javax.swing.*;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Field;

public abstract class AbstractDataField<T extends JComponent> implements IDataField {

    private static final Logger LOGGER = Logger.getLogger(AbstractDataField.class);

    private Field mappedField;
    private T component;
    private OffsetableObject mappedObject;

    public AbstractDataField(T component, Field mappedField) {
        this.component = component;
        this.mappedField = mappedField;
    }

    /**
     * {@inheritDoc}
     */
    @Override
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
    @Override
    public void saveToFile() {
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

        TableCreationUtils.updateObject(mappedObject, mappedObject.getClass());
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
