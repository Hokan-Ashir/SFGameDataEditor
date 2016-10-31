package sfgamedataeditor.views.common.notimplemented;

import sfgamedataeditor.events.processing.ViewRegister;
import sfgamedataeditor.mvc.objects.AbstractController;
import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.common.RenderableView;
import sfgamedataeditor.views.main.MainView;

import javax.swing.*;

public class NotImplementedView implements RenderableView {
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
    public void render() {
        MainView mainView = ViewRegister.INSTANCE.getView(MainView.class);
        mainView.renderViewInsideContentPanel(this);
    }

    @Override
    public void unrender() {

    }

    @Override
    public Class<? extends AbstractController> getControllerClass() {
        return null;
    }
}
