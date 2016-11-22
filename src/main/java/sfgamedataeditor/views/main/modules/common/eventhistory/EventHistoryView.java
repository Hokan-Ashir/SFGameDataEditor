package sfgamedataeditor.views.main.modules.common.eventhistory;

import sfgamedataeditor.mvc.objects.AbstractController;
import sfgamedataeditor.mvc.objects.ControllableView;
import sfgamedataeditor.utils.I18N;

import javax.swing.*;

public class EventHistoryView implements ControllableView {
    private JPanel mainPanel;
    private JButton undoButton;
    private JButton redoButton;

    public EventHistoryView() {
        undoButton.setText(I18N.INSTANCE.getMessage("back"));
        redoButton.setText(I18N.INSTANCE.getMessage("forward"));
        
        undoButton.setEnabled(false);
        redoButton.setEnabled(false);
        attachUndoButtonListener();
        attachRedoButtonListener();
    }

    private void attachUndoButtonListener() {
        UndoButtonListener listener = new UndoButtonListener(undoButton, redoButton);
        undoButton.addActionListener(listener);
    }

    private void attachRedoButtonListener() {
        RedoButtonListener listener = new RedoButtonListener(undoButton, redoButton);
        redoButton.addActionListener(listener);
    }

    public void setRedoButtonStatus(boolean isEnabled) {
        redoButton.setEnabled(isEnabled);
    }

    public void setUndoButtonStatus(boolean isEnabled) {
        undoButton.setEnabled(isEnabled);
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
