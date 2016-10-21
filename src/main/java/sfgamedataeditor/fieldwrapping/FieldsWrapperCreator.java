package sfgamedataeditor.fieldwrapping;

import sfgamedataeditor.fieldwrapping.fields.AbstractDataField;
import sfgamedataeditor.fieldwrapping.fields.IDataField;

import javax.swing.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;

public final class FieldsWrapperCreator {
    private FieldsWrapperCreator() {}

    public static Collection<IDataField> createFieldWrappers(Wrapable wrapable) {
        Collection<IDataField> wrappedFields = new ArrayList<>();
        for (Field field : wrapable.getClass().getDeclaredFields()) {
            MappedColumn mappedColumn = field.getAnnotation(MappedColumn.class);
            if (mappedColumn == null) {
                continue;
            }

            if (!mappedColumn.daoClass().isAssignableFrom(wrapable.getWrappableClass())) {
                continue;
            }

            Field mappedField = findMappedField(mappedColumn);
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

            final Class<? extends JComponent> type = (Class<? extends JComponent>) field.getType();

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

    private static Field findMappedField(MappedColumn mappedColumn) {
        String name = mappedColumn.name();
        Class<?> mappedClass = mappedColumn.daoClass();
        Field[] declaredFields = mappedClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if (declaredField.getName().equals(name)) {
                return declaredField;
            }
        }

        return null;
    }
}
