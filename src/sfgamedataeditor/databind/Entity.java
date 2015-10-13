package sfgamedataeditor.databind;

import javax.swing.*;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Entity extends AbstractEntity {

    private int[] value;
    private JTextField field;
    private int dataLength;

    public Entity(JTextField field, long offsetInBytes, int dataLengthInBytes) {
        this.field = field;
        setOffsetInFile(offsetInBytes);
        this.dataLength = dataLengthInBytes;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void loadDataFromFile(RandomAccessFile file) {
        value = getValueFromFile(file, dataLength);
        String fieldValue = createFieldValue();
        field.setText(fieldValue);
    }

    private String createFieldValue() {
        long temp = 0;
        for (int i = 0; i < value.length; i++) {
            temp += value[i] << ((value.length - 1 - i) * 8);
        }

        return String.valueOf(temp);
    }

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
}
