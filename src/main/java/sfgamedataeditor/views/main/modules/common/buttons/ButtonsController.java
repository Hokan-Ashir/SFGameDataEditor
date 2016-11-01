package sfgamedataeditor.views.main.modules.common.buttons;

import sfgamedataeditor.events.processing.ViewRegister;
import sfgamedataeditor.mvc.objects.AbstractController;
import sfgamedataeditor.views.main.MainView;

public class ButtonsController extends AbstractController<Object, ButtonsView> {
    public ButtonsController(ButtonsView view) {
        super(view);
    }

    @Override
    public void updateView() {

    }

    @Override
    public void renderView() {
        MainView view = ViewRegister.INSTANCE.getView(MainView.class);
        view.renderViewInsideButtonPanel(getView());
    }

    @Override
    public void unRenderView() {

    }
}
