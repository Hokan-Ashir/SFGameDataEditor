package sfgamedataeditor.databind.entity;

import org.apache.log4j.Logger;
import sfgamedataeditor.databind.files.FilesContainer;

import javax.swing.*;
import java.io.IOException;
import java.io.RandomAccessFile;

public abstract class Entity<T extends JComponent> extends AbstractEntity {

    private static final Logger LOGGER = Logger.getLogger(Entity.class);

    private int[] value;
    private T component;
    private int dataLength;

    public Entity(T component, long offsetInBytes, int dataLengthInBytes) {
        this.component = component;
        setOffsetInFile(offsetInBytes);
        this.dataLength = dataLengthInBytes;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void loadDataFromFile(RandomAccessFile file) {
        value = getValueFromFile(file, dataLength);
        viewFieldValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveDataInFile(RandomAccessFile file) {
        if (value == null) {
            return;
        }

        long offset = getFullFileOffset();
        try {
            file.seek(offset);
            for (int i = 0; i < value.length; i++) {
                file.write(value[value.length - 1 - i]);
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    protected int getFieldValue() {
        int temp = 0;
        for (int i = 0; i < getValue().length; i++) {
            temp += getValue()[i] << ((getValue().length - 1 - i) * 8);
        }

        return temp;
    }

    protected void setFieldValue(int value) {
        int i = 0;
        while (value > 0) {
            getValue()[getValue().length - 1 - i] = value & 0xFF;
            value >>= 8;
            i++;
        }

        saveDataInFile(FilesContainer.getModificationFile());
    }

    protected abstract void viewFieldValue();

    private int[] getValueFromFile(RandomAccessFile file, int length) {
        long offset = getFullFileOffset();

        int[] data = new int[length];
        try {
            file.seek(offset);
            for (int i = data.length - 1; i > -1; --i) {
                data[i] = file.readUnsignedByte();
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return data;
    }

    protected T getComponent() {
        return component;
    }

    protected int[] getValue() {
        return value;
    }
}
