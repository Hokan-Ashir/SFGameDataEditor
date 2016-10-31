package sfgamedataeditor.views.common.notimplemented;

import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.common.AbstractView;

import javax.swing.*;

public class NotImplementedView extends AbstractView {
    private JLabel notImplementedLabel;
    private JPanel mainPanel;

    public NotImplementedView() {
        notImplementedLabel.setText(I18N.INSTANCE.getMessage("notImplemented"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }
}
