package sfgamedataeditor.views.main.modules.items.buildingplans;

import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.mvc.objects.PresentableView;
import sfgamedataeditor.views.common.views.AbstractRacesView;
import sfgamedataeditor.views.main.modules.items.buildingplans.buildings.BuildingsPlanListView;

public class BuildingPlansRacesView extends AbstractRacesView {

    @Override
    public Class<? extends AbstractPresenter> getPresenterClass() {
        return BuildingPlansRacesPresenter.class;
    }


    @Override
    protected Class<? extends PresentableView> getPanelsViewClass() {
        return BuildingsPlanListView.class;
    }
}
