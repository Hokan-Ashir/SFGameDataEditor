package sfgamedataeditor.database.common;

import org.apache.log4j.Logger;
import org.mozilla.universalchardet.UniversalDetector;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.util.Map;

public class TextSerializer extends DefaultSerializer {

    private static final Logger LOGGER = Logger.getLogger(TextSerializer.class);
    private UniversalDetector detector = new UniversalDetector(null);

    @Override
    public boolean deserialize(Map.Entry<DataPair, Field> dataPairFieldEntry, byte[] buffer, Object object) {
        boolean isPopulated = super.deserialize(dataPairFieldEntry, buffer, object);
        if (isPopulated) {
            return true;
        }

        int offset = dataPairFieldEntry.getKey().getOffset();
        int length = dataPairFieldEntry.getKey().getLength();

        try {
            Field field = dataPairFieldEntry.getValue();
            field.setAccessible(true);

            Class<?> daoFieldType = dataPairFieldEntry.getValue().getType();
            if (!daoFieldType.isAssignableFrom(String.class)) {
                return false;
            }

            String detectCharset = detectCharset(buffer, offset, length);
            String string;
            if (detectCharset == null) {
                string = new String(buffer, offset, length).trim();
                setEncoding(object, Charset.defaultCharset().name(), true);
            } else {
                // juniversalchardet will incorrectly detect "Cp1251" charset, considering "Cp1251" as MACCYRILLIC
                // so if detector actually detected anything, just admit - this is russian language encoded via "Cp1251"
                // 38285
//                if (detectCharset.equals("MACCYRILLIC") || detectCharset.equals("WINDOWS-1255") ||
//                        detectCharset.equals("ISO-8859-5") || detectCharset.equals("KOI8-R")
//                        || detectCharset.equals("WINDOWS-1252")) {
//                    string = new String(buffer, offset, length, Charset.forName("WINDOWS-1251")).trim();
//                } else {
                    string = new String(buffer, offset, length, Charset.forName(detectCharset)).trim();
//                }
                setEncoding(object, detectCharset, true);
            }
            setEncoding(object, Charset.defaultCharset().name(), false);
            field.set(object, string);
        } catch (IllegalAccessException e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }

        return true;
    }

    private void setEncoding(Object object, String value, boolean isSourceEncoding) {
        try {
            for (Field field : object.getClass().getDeclaredFields()) {
                Encoding annotation = field.getAnnotation(Encoding.class);
                if (annotation != null && annotation.isSource() == isSourceEncoding) {
                    field.setAccessible(true);
                    field.set(object, value);
                }
            }
        } catch (IllegalAccessException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    private String detectCharset(byte[] bytes, int offset, int length) {
        detector.handleData(bytes, offset, length);
        detector.dataEnd();

        String encoding = detector.getDetectedCharset();
        detector.reset();
        return encoding;
    }

    @Override
    public byte[] serialize(Map.Entry<DataPair, Field> dataPairFieldEntry, Object object, int objectByteLength) {
        byte[] serialize = super.serialize(dataPairFieldEntry, object, objectByteLength);
        if (serialize != null) {
            return serialize;
        }

        int offset = dataPairFieldEntry.getKey().getOffset();
        int length = dataPairFieldEntry.getKey().getLength();
        Field field = dataPairFieldEntry.getValue();
        field.setAccessible(true);
        byte[] result = new byte[objectByteLength];
        try {
            Class<?> type = field.getType();
            if (!type.isAssignableFrom(String.class)) {
                return null;
            }

            String value = (String) field.get(object);
            byte[] valueBytes = value.getBytes(getEncoding(object, false));
            String detectCharset = detectCharset(valueBytes, 0, valueBytes.length);
            byte[] bytes;
            if (detectCharset == null) {
                bytes = valueBytes;
            } else {
                try {
                    bytes = value.getBytes(Charset.forName(getEncoding(object, true)));
                } catch (IllegalArgumentException e) {
                    bytes = value.getBytes(Charset.defaultCharset().name());
                }
            }
            for (int i = offset, j = 0; i < (offset + length); i++, j++) {
                if (bytes.length <= j) {
                    result[i] = 0;
                } else {
                    result[i] = bytes[j];
                }
            }

        } catch (IllegalAccessException | UnsupportedEncodingException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }

        return result;
    }

    private String getEncoding(Object object, boolean isSourceEncoding) {
        try {
            for (Field field : object.getClass().getDeclaredFields()) {
                Encoding annotation = field.getAnnotation(Encoding.class);
                if (annotation != null && annotation.isSource() == isSourceEncoding) {
                    field.setAccessible(true);
                    return (String) field.get(object);
                }
            }
        } catch (IllegalAccessException e) {
            LOGGER.error(e.getMessage(), e);
            return "";
        }

        return "";
    }
}
