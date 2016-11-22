package sfgamedataeditor.common.widgets.casttype;

import sfgamedataeditor.fieldwrapping.AbstractFieldListener;

import javax.swing.*;
import java.lang.reflect.Field;

public class CastTypeFormListener extends AbstractFieldListener {

    public CastTypeFormListener(JComponent component, Field mappedField) {
        super(component, mappedField);
    }

    @Override
    protected int getFieldValue() {
        return 0;
    }

    @Override
    protected void setFieldValue(int value) {

    }
}
