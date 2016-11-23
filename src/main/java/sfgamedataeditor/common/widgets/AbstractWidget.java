package sfgamedataeditor.common.widgets;

import javax.swing.*;
import java.lang.reflect.Field;

public abstract class AbstractWidget<T> extends JPanel implements Widget<T> {
    private final Field DTOField;

    public AbstractWidget(Field DTOField) {
        this.DTOField = DTOField;
    }

    public Field getDTOField() {
        return DTOField;
    }
}
