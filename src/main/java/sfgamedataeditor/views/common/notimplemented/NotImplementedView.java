package sfgamedataeditor.views.common.notimplemented;

import sfgamedataeditor.mvc.objects.AbstractController;
import sfgamedataeditor.mvc.objects.ControllableView;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;

public class NotImplementedView implements ControllableView {
    private JLabel notImplementedLabel;
    private JPanel mainPanel;

    public NotImplementedView() {
        notImplementedLabel.setText(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "notImplemented"));
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
