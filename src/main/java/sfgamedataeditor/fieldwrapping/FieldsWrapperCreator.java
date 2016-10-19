package sfgamedataeditor.fieldwrapping;

import sfgamedataeditor.fieldwrapping.fields.AbstractDataField;
import sfgamedataeditor.fieldwrapping.fields.IDataField;

import javax.swing.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public final class FieldsWrapperCreator {
    private FieldsWrapperCreator() {}

    public static Collection<IDataField> createFieldWrappers(Wrapable wrapable) {
        Collection<IDataField> wrappedFields = new ArrayList<>();
        MappedClass mappedClass = wrapable.getClass().getAnnotation(MappedClass.class);
        if (mappedClass == null) {
            return Collections.emptyList();
        }

        for (Field field : wrapable.getClass().getDeclaredFields()) {
            MappedColumn mappedColumn = field.getAnnotation(MappedColumn.class);
            if (mappedColumn == null) {
                continue;
            }

            Field mappedField = findMappedField(mappedColumn, mappedClass);
            Object fieldComponent = null;
            try {
                field.setAccessible(true);
                fieldComponent = field.get(wrapable);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            // TODO currently 3 parameters
            Object[] parameters = new Object[]{
                    fieldComponent,
                    mappedField
            };

            final Class<JComponent> type = (Class<JComponent>) field.getType();

            final AbstractDataField dataField = FieldFactory.INSTANCE.createField(type, parameters);
            if (field.isAnnotationPresent(Disabled.class)) {
                dataField.getComponent().setEnabled(false);
            } else if (field.isAnnotationPresent(Linkable.class)) {
                // TODO
            }

            wrappedFields.add(dataField);
        }

        return wrappedFields;
    }

    private static Field findMappedField(MappedColumn mappedColumn, MappedClass mappedClass) {
        String name = mappedColumn.name();
        Field[] declaredFields = mappedClass.mappedClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if (declaredField.getName().equals(name)) {
                return declaredField;
            }
        }

        return null;
    }
}
