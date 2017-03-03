package sfgamedataeditor.views.main.modules.items.buildingplans;

import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.views.common.AbstractModulesView;
import sfgamedataeditor.views.main.modules.items.buildingplans.buildings.BuildingsPlanListView;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.util.Set;
import java.util.TreeSet;

public class BuildingPlansRacesView extends AbstractModulesView {

    public BuildingPlansRacesView() {
        super(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "races"));
    }

    @Override
    protected void fillSubViewsMappings() {
        Set<String> mappings = new TreeSet<String>() {{
            add(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "races.humans"));
            add(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "races.elves"));
            add(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "races.dwarves"));
            add(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "races.orcs"));
            add(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "races.trolls"));
            add(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "races.dark.elves"));
        }};

        addMappings(mappings, BuildingsPlanListView.class);
    }

    @Override
    public Class<? extends AbstractPresenter> getPresenterClass() {
        return BuildingPlansRacesPresenter.class;
    }
}
