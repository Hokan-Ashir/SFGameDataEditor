package sfgamedataeditor.views.main.modules.buildings.races;

import sfgamedataeditor.database.buildings.common.BuildingsTableService;
import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.views.common.races.AbstractRacesView;
import sfgamedataeditor.views.main.modules.buildings.races.buildings.BuildingsView;

import java.util.Set;

public class BuildingRacesView extends AbstractRacesView {
    @Override
    protected void fillSubViewsMappings() {
        Set<String> buildingsRacesNames = BuildingsTableService.INSTANCE.getBuildingsRacesNames();
        addMappings(buildingsRacesNames, BuildingsView.class);
    }

    @Override
    public Class<? extends AbstractPresenter> getPresenterClass() {
        return BuildingRacesPresenter.class;
    }
}
