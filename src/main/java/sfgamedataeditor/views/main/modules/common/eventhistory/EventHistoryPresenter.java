package sfgamedataeditor.views.main.modules.common.eventhistory;

import sfgamedataeditor.events.processing.ViewRegister;
import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.views.main.MainView;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import javax.swing.*;

public class EventHistoryPresenter extends AbstractPresenter<EventHistoryModelParameter, EventHistoryView> {

    public EventHistoryPresenter(EventHistoryView view) {
        super(view);
        JButton undoButton = getView().getUndoButton();
        JButton redoButton = getView().getRedoButton();

        undoButton.setText(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "back"));
        redoButton.setText(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "forward"));

        undoButton.setEnabled(false);
        redoButton.setEnabled(false);
        attachUndoButtonListener();
        attachRedoButtonListener();
    }

    private void attachUndoButtonListener() {
        JButton undoButton = getView().getUndoButton();
        JButton redoButton = getView().getRedoButton();

        UndoButtonListener listener = new UndoButtonListener(undoButton, redoButton);
        undoButton.addActionListener(listener);
    }

    private void attachRedoButtonListener() {
        JButton undoButton = getView().getUndoButton();
        JButton redoButton = getView().getRedoButton();

        RedoButtonListener listener = new RedoButtonListener(undoButton, redoButton);
        redoButton.addActionListener(listener);
    }

    @Override
    public void updateView() {
        if (getModel() == null) {
            return;
        }

        setUndoButtonStatus(getModel().getParameter().isUndoPossible());
        setRedoButtonStatus(getModel().getParameter().isRedoPossible());
    }

    public void setRedoButtonStatus(boolean isEnabled) {
        getView().getRedoButton().setEnabled(isEnabled);
    }

    public void setUndoButtonStatus(boolean isEnabled) {
        getView().getUndoButton().setEnabled(isEnabled);
    }

    @Override
    public void renderView() {
        MainView mainView = ViewRegister.INSTANCE.getView(MainView.class);
        mainView.renderViewInsideEventHistoryPanel(getView());
    }

    @Override
    public void unRenderView() {

    }
}
