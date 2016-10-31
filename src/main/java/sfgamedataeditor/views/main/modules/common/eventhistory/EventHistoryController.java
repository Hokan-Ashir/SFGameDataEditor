package sfgamedataeditor.views.main.modules.common.eventhistory;

import sfgamedataeditor.mvc.objects.AbstractController;

public class EventHistoryController extends AbstractController<EventHistoryModelParameter, EventHistoryView> {

    public EventHistoryController(EventHistoryView view) {
        super(view);
    }

    @Override
    public void updateView() {
        getView().setUndoButtonStatus(getModel().getParameter().isUndoPossible());
        getView().setRedoButtonStatus(getModel().getParameter().isRedoPossible());
    }
}
