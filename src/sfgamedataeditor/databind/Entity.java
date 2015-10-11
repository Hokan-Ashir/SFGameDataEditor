package sfgamedataeditor.databind;

import javax.swing.*;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Entity extends AbstractEntity {

    private byte[] value;
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
        byte[] data = getValueFromFile(file, dataLength);
        String fieldValue = createFieldValue();
        field.setText(fieldValue);
        value = data;
    }

    private String createFieldValue() {
        long temp = 0;
        for (int i = 0; i < value.length; i++) {
            temp += value[i] << (i * 0xFF);
        }

        return String.valueOf(temp);
    }

    private byte[] getValueFromFile(RandomAccessFile file, int length) {
        long offset = getFullFileOffset();

        byte[] data = new byte[length];
        try {
            file.seek(offset);
            file.read(data);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }
}
