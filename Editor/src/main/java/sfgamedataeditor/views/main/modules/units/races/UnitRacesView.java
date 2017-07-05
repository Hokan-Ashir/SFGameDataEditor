package sfgamedataeditor.views.main.modules.units.races;

import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.mvc.objects.PresentableView;
import sfgamedataeditor.views.common.views.AbstractRacesView;
import sfgamedataeditor.views.main.modules.units.races.units.UnitListView;

public class UnitRacesView extends AbstractRacesView {

    @Override
    protected Class<? extends PresentableView> getPanelsViewClass() {
        return UnitListView.class;
    }

    @Override
    public Class<? extends AbstractPresenter> getPresenterClass() {
        return UnitRacesPresenter.class;
    }
}
