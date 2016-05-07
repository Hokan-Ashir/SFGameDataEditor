package sfgamedataeditor.fieldwrapping.fields;

import org.apache.log4j.Logger;
import sfgamedataeditor.databind.files.FilesContainer;

import javax.swing.*;
import java.io.IOException;
import java.io.RandomAccessFile;

public abstract class AbstractDataField<T extends JComponent> implements IDataField {

    private static final Logger LOGGER = Logger.getLogger(AbstractDataField.class);

    private final T component;
    private final long fieldOffset;
    private final int lengthInBytes;
    private long offsetInBytes;

    public AbstractDataField(T component, long fieldOffset, int lengthInBytes) {
        this.component = component;
        this.fieldOffset = fieldOffset;
        this.lengthInBytes = lengthInBytes;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void loadFromFile() {
        int value = loadDataFromFile(offsetInBytes, lengthInBytes);
        setFieldValue(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveToFile() {
        int value = getFieldValue();
        saveDataInFile(offsetInBytes, lengthInBytes, value);
    }

    protected abstract int getFieldValue();

    protected abstract void setFieldValue(int value);

    public double getFieldMaximumValue() {
        return Math.pow(2.0, 8 * lengthInBytes) - 1;
    }

    /**
     * {@inheritDoc}
     */
    public void setOffsetInBytes(long offsetInBytes) {
        this.offsetInBytes = offsetInBytes + fieldOffset;
    }

    public T getComponent() {
        return component;
    }

    private int loadDataFromFile(long fileOffset, int dataLength) {
        RandomAccessFile file = FilesContainer.INSTANCE.getModificationFile();
        int[] value = getDataFromFile(file, fileOffset, dataLength);
        return getValue(value);
    }

    private int[] getDataFromFile(RandomAccessFile file, long fileOffset, int length) {
        int[] data = new int[length];
        try {
            file.seek(fileOffset);
            for (int i = data.length - 1; i > -1; --i) {
                data[i] = file.readUnsignedByte();
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return data;
    }

    private int getValue(int[] value) {
        int temp = 0;
        for (int i = 0; i < value.length; i++) {
            temp += value[i] << ((value.length - 1 - i) * 8);
        }

        return temp;
    }

    private void saveDataInFile(long offset, int dataLength, int fieldValue) {
        RandomAccessFile file = FilesContainer.INSTANCE.getModificationFile();
        int[] value = getValue(fieldValue, dataLength);
        if (value == null) {
            return;
        }

        try {
            file.seek(offset);
            for (int i = 0; i < value.length; i++) {
                file.write(value[value.length - 1 - i]);
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    private int[] getValue(int value, int dataLength) {
        int[] temp = new int[dataLength];
        int i = 0;
        while (value > 0) {
            temp[temp.length - 1 - i] = value & 0xFF;
            value >>= 8;
            i++;
        }

        return temp;
    }
}
