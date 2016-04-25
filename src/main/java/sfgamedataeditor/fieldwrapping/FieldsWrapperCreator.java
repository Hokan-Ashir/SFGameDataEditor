package sfgamedataeditor.fieldwrapping;

import sfgamedataeditor.fieldwrapping.fields.AbstractDataField;
import sfgamedataeditor.fieldwrapping.fields.IDataField;
import sfgamedataeditor.utils.EntityTuple;

import javax.swing.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public final class FieldsWrapperCreator {
    private FieldsWrapperCreator() {}

    public static Collection<IDataField> createFieldWrappers(Wrapable wrapable) {
        Collection<IDataField> wrappedFields = new ArrayList<>();
        Map<Field, EntityTuple> fieldRestrictions = wrapable.getFieldDataRestrictions();
        for (Field field : wrapable.getClass().getDeclaredFields()) {
            final Class<JComponent> type = (Class<JComponent>) field.getType();
            if (!FieldFactory.INSTANCE.isClassExistsInFactory(type)) {
                continue;
            }

            final AbstractDataField dataField = FieldFactory.INSTANCE.createField(type, fieldRestrictions.get(field));
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
