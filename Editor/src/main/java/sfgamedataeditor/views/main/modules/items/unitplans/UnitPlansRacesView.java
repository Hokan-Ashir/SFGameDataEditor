package sfgamedataeditor.views.main.modules.items.unitplans;

import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.mvc.objects.PresentableView;
import sfgamedataeditor.views.common.views.AbstractRacesView;
import sfgamedataeditor.views.main.modules.items.unitplans.units.UnitsPlanListView;

public class UnitPlansRacesView extends AbstractRacesView {

    @Override
    public Class<? extends AbstractPresenter> getPresenterClass() {
        return UnitPlansRacesPresenter.class;
    }

    @Override
    protected Class<? extends PresentableView> getPanelsViewClass() {
        return UnitsPlanListView.class;
    }
}
