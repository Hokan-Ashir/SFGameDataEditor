package sfgamedataeditor.views.main.modules.common.eventhistory;

import sfgamedataeditor.mvc.objects.AbstractController;
import sfgamedataeditor.mvc.objects.ControllableView;

import javax.swing.*;

public class EventHistoryView implements ControllableView {
    private JPanel mainPanel;
    private JButton undoButton;
    private JButton redoButton;

    public JButton getUndoButton() {
        return undoButton;
    }

    public JButton getRedoButton() {
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
    public Class<? extends AbstractController> getControllerClass() {
        return EventHistoryController.class;
    }
}
