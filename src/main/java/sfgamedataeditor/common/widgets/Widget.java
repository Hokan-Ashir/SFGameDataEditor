package sfgamedataeditor.common.widgets;

import sfgamedataeditor.mvc.objects.Model;

import javax.swing.*;
import java.util.EventListener;

public interface Widget {
    void attachListener(EventListener listener);
    void update(Model model);
    void updateI18N();
    JPanel getMainPanel();
}
