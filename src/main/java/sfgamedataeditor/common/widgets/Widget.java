package sfgamedataeditor.common.widgets;

import javax.swing.*;

public interface Widget<T> {
    void update(T model);
    void updateI18N();
    JPanel getMainPanel();
}
