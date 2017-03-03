package sfgamedataeditor.views.main.modules.items.buildingplans.buildings;

import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.views.common.AbstractModulesView;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

public class BuildingsPlanListView extends AbstractModulesView {

    public BuildingsPlanListView() {
        super(I18NService.INSTANCE.getMessage(I18NTypes.BUILDING_GUI, "moduleName"));
    }

    @Override
    protected void fillSubViewsMappings() {

    }

    @Override
    public Class<? extends AbstractPresenter> getPresenterClass() {
        return BuildingsPlanListPresenter.class;
    }
}
