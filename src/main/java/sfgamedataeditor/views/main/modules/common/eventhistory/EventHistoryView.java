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
        attachUndoButtonListener();
        attachRedoButtonListener();
    }

    private void attachUndoButtonListener() {
        undoButton.addActionListener(new UndoButtonListener());
    }

    private void attachRedoButtonListener() {
        redoButton.addActionListener(new RedoButtonListener());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }
}
