package sfgamedataeditor.database.common;

import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.util.Map;

public class DefaultSerializer implements Serializer {

    private static final Logger LOGGER = Logger.getLogger(DefaultSerializer.class);

    @Override
    public boolean deserialize(Map.Entry<DataPair, Field> dataPairFieldEntry, byte[] buffer, Object object) {
        int offset = dataPairFieldEntry.getKey().getOffset();
        int length = dataPairFieldEntry.getKey().getLength();

        try {
            Field field = dataPairFieldEntry.getValue();
            field.setAccessible(true);

            Class<?> daoFieldType = dataPairFieldEntry.getValue().getType();
            if (!Integer.class.isAssignableFrom(daoFieldType)) {
                return false;
            }

            int temp = getValue(buffer, offset, length);
            field.set(object, temp);
        } catch (IllegalAccessException e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }

        return true;
    }

    @Override
    public byte[] serialize(Map.Entry<DataPair, Field> dataPairFieldEntry, Object object, int objectByteLength) {
        int offset = dataPairFieldEntry.getKey().getOffset();
        int length = dataPairFieldEntry.getKey().getLength();
        Field field = dataPairFieldEntry.getValue();
        field.setAccessible(true);
        byte[] result = new byte[objectByteLength];
        try {
            Class<?> type = field.getType();
            if (!Integer.class.isAssignableFrom(type)) {
                return null;
            }

            int value = (int) field.get(object);
            for (int i = offset; i < offset + length; i++) {
                byte b = (byte) (value & 0xFF);
                result[i] = b;
                value >>= 8;
            }
        } catch (IllegalAccessException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }

        return result;
    }

    private int getValue(byte[] value, int offset, int length) {
        int temp = 0;
        for (int i = offset; i < offset + length; i++) {
            temp += (value[i] & 0xFF) << ((i - offset) * 8);
        }

        return temp;
    }
}
