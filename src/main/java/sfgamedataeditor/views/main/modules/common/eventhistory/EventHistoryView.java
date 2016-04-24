package sfgamedataeditor.views.main.modules.common.eventhistory;

import sfgamedataeditor.views.common.AbstractView;
import sfgamedataeditor.views.main.MainView;

import javax.swing.*;

public class EventHistoryView extends AbstractView<MainView> {
    private JPanel mainPanel;
    private JButton undoButton;
    private JButton redoButton;

    public EventHistoryView(MainView parentView) {
        super(parentView);
        undoButton.setEnabled(false);
        redoButton.setEnabled(false);
        attachUndoButtonListener();
        attachRedoButtonListener();
    }

    private void attachUndoButtonListener() {
        UndoButtonListener listener = new UndoButtonListener(redoButton, undoButton);
        undoButton.addActionListener(listener);
    }

    private void attachRedoButtonListener() {
        RedoButtonListener listener = new RedoButtonListener(redoButton, undoButton);
        redoButton.addActionListener(listener);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateData(Object data) {
        redoButton.setEnabled(EventHistory.INSTANCE.isRedoPossible());
        undoButton.setEnabled(EventHistory.INSTANCE.isUndoPossible());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }
}
