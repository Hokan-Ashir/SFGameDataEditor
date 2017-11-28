package sfgamedataeditor.database.common;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public enum ObjectDataMappingService {
    INSTANCE;

    private final Map<Class<?>, Map<DataPair, Field>> map = new HashMap<>();

    public void fillObjectWithData(Object object, byte[] buffer, Serializer serializer) {
        Class<?> aClass = object.getClass();
        Map<DataPair, Field> objectMapping = getObjectMapping(aClass);
        for (Map.Entry<DataPair, Field> dataPairFieldEntry : objectMapping.entrySet()) {
            serializer.deserialize(dataPairFieldEntry, buffer, object);
        }
    }

    private Map<DataPair, Field> getObjectMapping(Class<?> ormObject) {
        if (!map.containsKey(ormObject)) {
            Map<DataPair, Field> innerMap = new TreeMap<>();
            Field[] declaredFields = ormObject.getDeclaredFields();
            for (Field field : declaredFields) {
                boolean isDataField = field.isAnnotationPresent(Data.class);
                if (!isDataField) {
                    continue;
                }

                Data data = field.getAnnotation(Data.class);
                DataPair pair = new DataPair(data.length(), data.offset());
                innerMap.put(pair, field);
            }

            map.put(ormObject, innerMap);
        }

        return map.get(ormObject);
    }

    public byte[] serializeObject(Object daoObject, Serializer serializer) {
        Class<?> aClass = daoObject.getClass();
        Map<DataPair, Field> objectMapping = getObjectMapping(aClass);
        DataPair lastDataPair = (DataPair) ((TreeMap) objectMapping).lastKey();
        int objectByteLength = lastDataPair.getOffset() + lastDataPair.getLength();
        byte[] result = new byte[objectByteLength];

        for (Map.Entry<DataPair, Field> dataPairFieldEntry : objectMapping.entrySet()) {
            byte[] serialize = serializer.serialize(dataPairFieldEntry, daoObject, objectByteLength);
            int offset = dataPairFieldEntry.getKey().getOffset();
            int length = dataPairFieldEntry.getKey().getLength();
            System.arraycopy(serialize, offset, result, offset, offset + length - offset);
        }

        return result;
    }
}
