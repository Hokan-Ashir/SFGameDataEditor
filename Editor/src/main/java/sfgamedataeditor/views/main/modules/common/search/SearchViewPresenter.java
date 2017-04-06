package sfgamedataeditor.views.main.modules.common.search;

import sfgamedataeditor.events.processing.ViewRegister;
import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.views.main.MainView;

public class SearchViewPresenter extends AbstractPresenter<Object, SearchView> {

    public SearchViewPresenter(SearchView view) {
        super(view);
    }

    @Override
    public void updateView() {

    }

    @Override
    public void renderView() {
        MainView mainView = ViewRegister.INSTANCE.getView(MainView.class);
        mainView.renderViewInsideHeaderPanel(getView().getMainPanel());
    }

    @Override
    public void unRenderView() {

    }
}
