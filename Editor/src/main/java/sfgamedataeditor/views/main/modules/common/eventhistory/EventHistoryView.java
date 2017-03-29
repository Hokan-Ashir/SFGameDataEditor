package sfgamedataeditor.views.main.modules.common.eventhistory;

import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.mvc.objects.PresentableView;

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
}
