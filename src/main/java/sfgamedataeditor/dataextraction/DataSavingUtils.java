package sfgamedataeditor.dataextraction;

import org.apache.log4j.Logger;
import sfgamedataeditor.databind.files.FilesContainer;

import java.io.IOException;
import java.io.RandomAccessFile;

public final class DataSavingUtils {

    private static final Logger LOGGER = Logger.getLogger(DataSavingUtils.class);

    private DataSavingUtils() {
    }

    public static int loadDataFromFile(long fileOffset, int dataLength) {
        RandomAccessFile file = FilesContainer.INSTANCE.getModificationFile();
        int[] value = getDataFromFile(file, fileOffset, dataLength);
        return getValue(value);
    }

    private static int[] getDataFromFile(RandomAccessFile file, long fileOffset, int length) {
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

    private static int getValue(int[] value) {
        int temp = 0;
        for (int i = 0; i < value.length; i++) {
            temp += value[i] << ((value.length - 1 - i) * 8);
        }

        return temp;
    }

    public static void saveDataInFile(long offset, int fieldValue) {
        RandomAccessFile file = FilesContainer.INSTANCE.getModificationFile();
        int[] value = getValue(fieldValue);
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

    private static int[] getValue(int value) {
        int[] temp = new int[Integer.bitCount(value) / 8 + 1];
        int i = 0;
        while (value > 0) {
            temp[temp.length - 1 - i] = value & 0xFF;
            value >>= 8;
            i++;
        }

        return temp;
    }
}
