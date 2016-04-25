package sfgamedataeditor.fieldwrapping.fields;

import sfgamedataeditor.dataextraction.DataSavingUtils;
import sfgamedataeditor.utils.EntityTuple;

import javax.swing.*;

public abstract class AbstractDataField<T extends JComponent> implements IDataField {

    private EntityTuple<T> tuple;

    public AbstractDataField(EntityTuple<T> tuple) {
        this.tuple = tuple;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void loadFromFile() {
        // TODO move this methods here
        int value = DataSavingUtils.loadDataFromFile(tuple.getOffsetInBytes(), tuple.getLengthInBytes());
        setFieldValue(value);
    }

    protected abstract void setFieldValue(int value);

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveToFile() {
        // TODO move this methods here
        int value = getFieldValue();
        DataSavingUtils.saveDataInFile(tuple.getOffsetInBytes(), value);
    }

    protected abstract int getFieldValue();

    protected EntityTuple<T> getTuple() {
        return tuple;
    }

    public T getComponent() {
        return tuple.getComponent();
    }
}
