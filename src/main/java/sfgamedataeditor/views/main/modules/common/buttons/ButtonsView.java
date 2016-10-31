package sfgamedataeditor.views.main.modules.common.buttons;

import sfgamedataeditor.events.processing.ViewRegister;
import sfgamedataeditor.mvc.objects.AbstractController;
import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.common.RenderableView;
import sfgamedataeditor.views.main.MainView;
import sfgamedataeditor.views.main.modules.common.buttons.listeners.CreateModificatedFileButtonListener;
import sfgamedataeditor.views.main.modules.common.buttons.listeners.CreateSfModFileButtonListener;
import sfgamedataeditor.views.main.modules.common.buttons.listeners.LoadSfModFileButtonListener;

import javax.swing.*;

public class ButtonsView implements RenderableView {
    private JButton loadSfmodFileButton;
    private JButton createSfmodFileButton;
    private JPanel mainPanel;
    private JButton createModificatedFileButton;

    public ButtonsView() {
        loadSfmodFileButton.setText(I18N.INSTANCE.getMessage("loadSfModFileButtonCaption"));
        createSfmodFileButton.setText(I18N.INSTANCE.getMessage("createSfModFileButtonCaption"));
        createModificatedFileButton.setText(I18N.INSTANCE.getMessage("createModificationFileButtonCaption"));

        createSfmodFileButton.addActionListener(new CreateSfModFileButtonListener());
        loadSfmodFileButton.addActionListener(new LoadSfModFileButtonListener());
        createModificatedFileButton.addActionListener(new CreateModificatedFileButtonListener());
    }

    @Override
    public void render() {
        MainView view = ViewRegister.INSTANCE.getView(MainView.class);
        view.renderViewInsideButtonPanel(this);
    }

    @Override
    public void unrender() {

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
