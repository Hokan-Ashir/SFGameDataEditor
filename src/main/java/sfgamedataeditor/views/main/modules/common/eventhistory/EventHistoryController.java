package sfgamedataeditor.views.main.modules.common.eventhistory;

import sfgamedataeditor.events.processing.ViewRegister;
import sfgamedataeditor.mvc.objects.AbstractController;
import sfgamedataeditor.views.main.MainView;

public class EventHistoryController extends AbstractController<EventHistoryModelParameter, EventHistoryView> {

    public EventHistoryController(EventHistoryView view) {
        super(view);
    }

    @Override
    public void updateView() {
        if (getModel() == null) {
            return;
        }

        getView().setUndoButtonStatus(getModel().getParameter().isUndoPossible());
        getView().setRedoButtonStatus(getModel().getParameter().isRedoPossible());
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
