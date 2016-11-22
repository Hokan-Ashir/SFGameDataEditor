package sfgamedataeditor.views.main.modules.common.buttons;

import sfgamedataeditor.mvc.objects.AbstractController;
import sfgamedataeditor.mvc.objects.ControllableView;
import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.main.modules.common.buttons.listeners.CreateModificatedFileButtonListener;
import sfgamedataeditor.views.main.modules.common.buttons.listeners.CreateSfModFileButtonListener;
import sfgamedataeditor.views.main.modules.common.buttons.listeners.LoadSfModFileButtonListener;

import javax.swing.*;

public class ButtonsView implements ControllableView {
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

    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }

    @Override
    public Class<? extends AbstractController> getControllerClass() {
        return ButtonsController.class;
    }
}
