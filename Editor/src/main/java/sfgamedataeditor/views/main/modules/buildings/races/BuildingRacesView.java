package sfgamedataeditor.views.main.modules.buildings.races;

import sfgamedataeditor.database.buildings.common.BuildingsTableService;
import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.views.common.views.AbstractModulesView;
import sfgamedataeditor.views.main.modules.buildings.races.buildings.BuildingsView;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.util.Set;

public class BuildingRacesView extends AbstractModulesView {

    public BuildingRacesView() {
        super(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "races"));
    }

    @Override
    public void fillSubViewsMappings() {
        Set<String> buildingsRacesNames = BuildingsTableService.INSTANCE.getBuildingsRacesNames();
        addMappings(buildingsRacesNames, BuildingsView.class);
    }

    @Override
    public Class<? extends AbstractPresenter> getPresenterClass() {
        return BuildingRacesPresenter.class;
    }
}
