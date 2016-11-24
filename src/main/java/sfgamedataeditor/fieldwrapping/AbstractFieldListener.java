package sfgamedataeditor.fieldwrapping;

import org.apache.log4j.Logger;
import sfgamedataeditor.common.widgets.AbstractWidget;
import sfgamedataeditor.database.objects.OffsetableObject;
import sfgamedataeditor.database.tableservices.CommonTableService;

import java.lang.reflect.Field;

public abstract class AbstractFieldListener<T extends AbstractWidget, M> {
    private static final Logger LOGGER = Logger.getLogger(AbstractFieldListener.class);

    private Field[] mappedFields;
    private T component;
    private M mappedObject;

    public AbstractFieldListener(T component, Field... mappedFields) {
        this.component = component;
        this.mappedFields = mappedFields;
    }

    /**
     * {@inheritDoc}
     */
    public void updateWidgetValue(M mappedObject) {
        this.mappedObject = mappedObject;
        try {
            int[] values = new int[mappedFields.length];
            for (int i = 0; i < mappedFields.length; ++i) {
                mappedFields[i].setAccessible(true);
                Object o = mappedFields[i].get(mappedObject);
                values[i] = (Integer)o;
            }

            setFieldValues(values);
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
        // the listener (RequirementClassSubClassListener) on it is launched to set subClass comboBox value
        // BUT there is no mapped object on this subClass comboBox, so NPE appears
        if (mappedObject == null) {
            return;
        }
        int values[] = getFieldValues();
        for (int i = 0; i <mappedFields.length; ++i) {
            mappedFields[i].setAccessible(true);
            try {
                mappedFields[i].set(mappedObject, values[i]);
            } catch (IllegalAccessException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }

        OffsetableObject mappedObject = (OffsetableObject) this.mappedObject;
        CommonTableService.INSTANCE.updateObject(mappedObject, mappedObject.getClass());
    }

    protected Field[] getMappedFields() {
        return mappedFields;
    }

    protected abstract int[] getFieldValues();

    protected abstract void setFieldValues(int[] value);

    public T getWidget() {
        return component;
    }
}
