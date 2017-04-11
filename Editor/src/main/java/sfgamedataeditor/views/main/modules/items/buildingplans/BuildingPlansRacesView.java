package sfgamedataeditor.views.main.modules.items.buildingplans;

import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.mvc.objects.PresentableView;
import sfgamedataeditor.views.common.views.AbstractRacesView;
import sfgamedataeditor.views.main.modules.items.buildingplans.buildings.BuildingsPlanListView;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

public class BuildingPlansRacesView extends AbstractRacesView {

    public BuildingPlansRacesView() {
        super(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "races"));
    }

    @Override
    public Class<? extends AbstractPresenter> getPresenterClass() {
        return BuildingPlansRacesPresenter.class;
    }

    @Override
    protected Class<? extends PresentableView> getSubModulesViewClass() {
        return BuildingsPlanListView.class;
    }
}
