package sfgamedataeditor.views.main.modules.common.localization;

import sfgamedataeditor.events.processing.ViewRegister;
import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.views.main.MainView;

public class LocalizationPresenter extends AbstractPresenter<Object, LocalizationView> {

    public LocalizationPresenter(LocalizationView view) {
        super(view);
    }

    @Override
    public void updateView() {

    }

    @Override
    public void renderView() {
        MainView view = ViewRegister.INSTANCE.getView(MainView.class);
        view.renderViewInsideLocalizationPanel(getView().getMainPanel());
    }

    @Override
    public void unRenderView() {

    }
}
