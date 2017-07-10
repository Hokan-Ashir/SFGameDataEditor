package sfgamedataeditor.views.main.modules.common.eventhistory;

import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.mvc.objects.PresentableView;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;

@SuppressWarnings("unused")
public class EventHistoryView implements PresentableView {
    private JPanel mainPanel;
    private JButton undoButton;
    private JButton redoButton;

    JButton getUndoButton() {
        return undoButton;
    }

    JButton getRedoButton() {
        return redoButton;
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
        return EventHistoryPresenter.class;
    }

    @Override
    public void localize() {
        undoButton.setText(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "back"));
        redoButton.setText(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "forward"));
    }
}
