package sfgamedataeditor.views.main.modules;

import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.mvc.objects.PresentableView;
import sfgamedataeditor.views.common.ObjectTuple;
import sfgamedataeditor.views.common.SubViewPanelTuple;
import sfgamedataeditor.views.common.views.AbstractModulesView;
import sfgamedataeditor.views.main.modules.buildings.races.BuildingRacesView;
import sfgamedataeditor.views.main.modules.creatures.races.CreaturesRacesView;
import sfgamedataeditor.views.main.modules.items.ItemTypesView;
import sfgamedataeditor.views.main.modules.objects.chests.ChestsListView;
import sfgamedataeditor.views.main.modules.player.level.stats.PlayerLevelStatsView;
import sfgamedataeditor.views.main.modules.spells.schools.SpellSchoolsView;
import sfgamedataeditor.views.main.modules.units.races.UnitRacesView;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.util.ArrayList;
import java.util.List;

public class ModulesView extends AbstractModulesView {

    public ModulesView() {
        super(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "modulesList"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fillSubViewsMappings() {
        List<SubViewPanelTuple> mappings = new ArrayList<SubViewPanelTuple>() {{
           add(createTuple("spells", SpellSchoolsView.class));
           add(createTuple("buildings", BuildingRacesView.class));
           add(createTuple("creatures", CreaturesRacesView.class));
           add(createTuple("items", ItemTypesView.class));
           add(createTuple("units", UnitRacesView.class));
           add(createTuple("chests", ChestsListView.class));
           add(createTuple("playerStats", PlayerLevelStatsView.class));
        }};

        addMappings(mappings);
    }

    private SubViewPanelTuple createTuple(String i18nKey, Class<? extends PresentableView> viewClass) {
        return new SubViewPanelTuple(new ObjectTuple(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, i18nKey), null), viewClass);
    }

    @Override
    public Class<? extends AbstractPresenter> getPresenterClass() {
        return ModulesPresenter.class;
    }
}
