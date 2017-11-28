package sfgamedataeditor.database.common;

import java.lang.reflect.Field;
import java.util.Map;

public interface Serializer {
    boolean deserialize(Map.Entry<DataPair, Field> dataPairFieldEntry, byte[] buffer, Object object);
    byte[] serialize(Map.Entry<DataPair, Field> dataPairFieldEntry, Object object, int objectByteLength);
}
