package sfgamedataeditor.views.main.modules.common.buttons;

import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.mvc.objects.PresentableView;
import sfgamedataeditor.views.main.modules.common.buttons.listeners.CreateModificatedFileButtonListener;
import sfgamedataeditor.views.main.modules.common.buttons.listeners.CreateSfModFileButtonListener;
import sfgamedataeditor.views.main.modules.common.buttons.listeners.LoadSfModFileButtonListener;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;

public class ButtonsView implements PresentableView {
    private JButton loadSfmodFileButton;
    private JButton createSfmodFileButton;
    private JPanel mainPanel;
    private JButton createModificatedFileButton;

    public ButtonsView() {
        loadSfmodFileButton.setText(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "loadSfModFileButtonCaption"));
        createSfmodFileButton.setText(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "createSfModFileButtonCaption"));
        createModificatedFileButton.setText(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "createModificationFileButtonCaption"));

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
    public Class<? extends AbstractPresenter> getPresenterClass() {
        return ButtonsPresenter.class;
    }
}
