package sfgamedataeditor.common.widgets;

import java.lang.reflect.Field;

public abstract class AbstractWidget implements Widget {
    private final Field DTOField;

    public AbstractWidget(Field DTOField) {
        this.DTOField = DTOField;
    }

    public Field getDTOField() {
        return DTOField;
    }
}
