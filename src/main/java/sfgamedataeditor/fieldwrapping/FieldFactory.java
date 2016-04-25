package sfgamedataeditor.fieldwrapping;

import sfgamedataeditor.fieldwrapping.fields.AbstractDataField;
import sfgamedataeditor.fieldwrapping.fields.TextField;
import sfgamedataeditor.utils.EntityTuple;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public enum  FieldFactory {
    INSTANCE;

    private Map<Class<JComponent>, Class<AbstractDataField>> fieldTypes = new HashMap<>();

    public <T extends AbstractDataField, C extends JComponent> T createField(Class<JComponent> componentClass, EntityTuple<C> tuple) {
        // TODO
        T field = (T) new TextField((EntityTuple<JTextField>) tuple);
        /*if (!fieldTypes.containsKey(componentClass)) {
            field = ...
        } else {
            field = fieldTypes.get(componentClass).getConstructor().newInstance()
        }*/

        return field;
    }

    public boolean isClassExistsInFactory(Class<JComponent> componentClass) {
        return fieldTypes.containsKey(componentClass);
    }
}
