package sfgamedataeditor.databind.entity;

import javax.swing.*;
import java.io.IOException;
import java.io.RandomAccessFile;

public abstract class Entity<T extends JComponent> extends AbstractEntity {

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
        setFieldValue();
    }

    protected abstract void setFieldValue();

    private int[] getValueFromFile(RandomAccessFile file, int length) {
        long offset = getFullFileOffset();

        int[] data = new int[length];
        try {
            file.seek(offset);
            for (int i = data.length - 1; i > -1; --i) {
                data[i] = file.readUnsignedByte();
            }
        } catch (IOException e) {
            e.printStackTrace();
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
