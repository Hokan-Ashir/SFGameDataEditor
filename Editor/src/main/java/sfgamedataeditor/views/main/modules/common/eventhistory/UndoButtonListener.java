package sfgamedataeditor.views.main.modules.common.eventhistory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class UndoButtonListener implements ActionListener {

    private final JButton redoButton;
    private final JButton undoButton;

    UndoButtonListener(JButton undoButton, JButton redoButton) {
        this.undoButton = undoButton;
        this.redoButton = redoButton;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        EventHistory.INSTANCE.undo();
        redoButton.setEnabled(EventHistory.INSTANCE.isRedoPossible());
        undoButton.setEnabled(EventHistory.INSTANCE.isUndoPossible());
    }
}
