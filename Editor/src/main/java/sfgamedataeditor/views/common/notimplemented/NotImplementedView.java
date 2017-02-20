package sfgamedataeditor.views.common.notimplemented;

import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.mvc.objects.PresentableView;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;

public class NotImplementedView implements PresentableView {
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
    public Class<? extends AbstractPresenter> getPresenterClass() {
        return null;
    }
}
