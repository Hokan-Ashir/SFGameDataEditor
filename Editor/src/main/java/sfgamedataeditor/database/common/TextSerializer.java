package sfgamedataeditor.database.common;

import org.apache.log4j.Logger;
import org.mozilla.universalchardet.UniversalDetector;

import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.util.Arrays;
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
            } else {
                // juniversalchardet will incorrectly detect "Cp1251" charset, considering "Cp1251" as MACCYRILLIC
                // so if detector actually detected anything, just admit - this is russian language encoded via "Cp1251"
                // 38285
                if (detectCharset.equals("MACCYRILLIC") || detectCharset.equals("IBM855") || detectCharset.equals("ISO-8859-8") || detectCharset.equals("WINDOWS-1255")
                        || detectCharset.equals("ISO-8859-7") || detectCharset.equals("ISO-8859-5") || detectCharset.equals("IBM866") || detectCharset.equals("KOI8-R")
                        || detectCharset.equals("WINDOWS-1252")) {
                    string = new String(buffer, offset, length, Charset.forName("WINDOWS-1251")).trim();
                } else {
                    string = new String(buffer, offset, length, Charset.forName(detectCharset)).trim();
                }
            }
            setOriginalContent(object,field, buffer, offset, length);
            field.set(object, string);
        } catch (IllegalAccessException e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }

        return true;
    }

    private void setOriginalContent(Object object, Field field, byte[] buffer, int offset, int length) {
        OriginalContent originalContent = field.getAnnotation(OriginalContent.class);
        if (originalContent == null) {
            LOGGER.error("No " + OriginalContent.class.getName() + " annotation presents over " + field.getName());
            return;
        }

        if (originalContent.isSource()) {
            LOGGER.error(OriginalContent.class.getName() + " is source annotation");
            return;
        }

        try {
            for (Field innerField : object.getClass().getDeclaredFields()) {
                OriginalContent annotation = innerField.getAnnotation(OriginalContent.class);
                if (annotation != null && annotation.isSource()
                        && annotation.number() == originalContent.number()) {
                    innerField.setAccessible(true);
                    innerField.set(object, Arrays.copyOfRange(buffer, offset, offset + length));
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
        Class<?> type = field.getType();
        if (!type.isAssignableFrom(String.class)) {
            return null;
        }


        byte[] bytes = getOriginalContent(object, field);
        for (int i = offset, j = 0; i < (offset + length); i++, j++) {
            if (bytes.length <= j) {
                result[i] = 0;
            } else {
                result[i] = bytes[j];
            }
        }

        return result;
    }

    private byte[] getOriginalContent(Object object, Field field) {
        OriginalContent originalContent = field.getAnnotation(OriginalContent.class);
        if (originalContent == null) {
            LOGGER.error("No " + OriginalContent.class.getName() + " annotation presents over " + field.getName());
            return null;
        }

        if (originalContent.isSource()) {
            LOGGER.error(OriginalContent.class.getName() + " is source annotation");
            return null;
        }

        try {
            for (Field innerField : object.getClass().getDeclaredFields()) {
                OriginalContent annotation = innerField.getAnnotation(OriginalContent.class);
                if (annotation != null && annotation.isSource() && annotation.number() == originalContent.number()) {
                    innerField.setAccessible(true);
                    return (byte[]) innerField.get(object);
                }
            }
        } catch (IllegalAccessException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }

        return null;
    }
}
