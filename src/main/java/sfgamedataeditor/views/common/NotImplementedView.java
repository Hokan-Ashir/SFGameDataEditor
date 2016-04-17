package sfgamedataeditor.views.common;

import sfgamedataeditor.utils.I18N;

import javax.swing.*;

public class NotImplementedView<T extends AbstractView> extends AbstractView<T> {
    private JLabel notImplementedLabel;
    private JPanel mainPanel;

    public NotImplementedView(T parentView) {
        super(parentView);
        notImplementedLabel.setText(I18N.INSTANCE.getMessage("notImplemented"));
    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }
}
