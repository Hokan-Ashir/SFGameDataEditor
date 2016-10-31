package sfgamedataeditor.views.common;

import javax.swing.*;

public interface IView {
    JPanel getMainPanel();
    void render();
    void unrender();
}
