package sfgamedataeditor.views.common.notimplemented;

import sfgamedataeditor.mvc.objects.AbstractController;
import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.common.ControllableView;

import javax.swing.*;

public class NotImplementedView implements ControllableView {
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

    @Override
    public Class<? extends AbstractController> getControllerClass() {
        return null;
    }
}
