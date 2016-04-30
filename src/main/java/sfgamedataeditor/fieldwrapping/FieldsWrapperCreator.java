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
            Data annotation = field.getAnnotation(Data.class);
            if (annotation == null) {
                continue;
            }

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
                    annotation.offset(),
                    annotation.length()
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
}
