package sfgamedataeditor.views.common;

import sfgamedataeditor.mvc.objects.PresentableView;

import javax.swing.*;

public class SubViewPanel {
    private final JButton button;
    private final Class<? extends PresentableView> subViewClass;

    public SubViewPanel(JButton button, Class<? extends PresentableView> subViewClass) {
        this.button = button;
        this.subViewClass = subViewClass;
    }

    public JButton getButton() {
        return button;
    }

    public Class<? extends PresentableView> getSubViewClass() {
        return subViewClass;
    }
}
