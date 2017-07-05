package sfgamedataeditor.views.main.modules.items.workersrunes;

import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.mvc.objects.PresentableView;
import sfgamedataeditor.views.common.views.AbstractRacesView;
import sfgamedataeditor.views.main.modules.items.workersrunes.parameters.WorkersRunesParametersView;

public class WorkersRuneRacesView extends AbstractRacesView {

    @Override
    public Class<? extends AbstractPresenter> getPresenterClass() {
        return WorkersRuneRacesPresenter.class;
    }

    @Override
    protected Class<? extends PresentableView> getPanelsViewClass() {
        return WorkersRunesParametersView.class;
    }
}
