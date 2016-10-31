package sfgamedataeditor.views.main.modules.common.eventhistory;

import sfgamedataeditor.mvc.objects.AbstractController;
import sfgamedataeditor.mvc.objects.Model;

public class EventHistoryController extends AbstractController<EventHistoryModelParameter, EventHistoryView> {

    public EventHistoryController(Model<EventHistoryModelParameter> model, EventHistoryView view) {
        super(model, view);
    }

    @Override
    public void updateView() {
        getView().setUndoButtonStatus(getModel().getParameter().isUndoPossible());
        getView().setRedoButtonStatus(getModel().getParameter().isRedoPossible());
    }
}
