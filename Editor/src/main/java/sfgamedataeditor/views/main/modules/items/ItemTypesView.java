package sfgamedataeditor.views.main.modules.items;

import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.mvc.objects.PresentableView;
import sfgamedataeditor.views.common.SubViewPanelTuple;
import sfgamedataeditor.views.common.views.AbstractModulesView;
import sfgamedataeditor.views.main.modules.items.armor.ArmorTypeListView;
import sfgamedataeditor.views.main.modules.items.buildingplans.BuildingPlansRacesView;
import sfgamedataeditor.views.main.modules.items.herorunes.HeroesRunesListView;
import sfgamedataeditor.views.main.modules.items.miscellaneous.MiscellaneousListView;
import sfgamedataeditor.views.main.modules.items.spellscrolls.schools.SpellScrollsListView;
import sfgamedataeditor.views.main.modules.items.unitplans.UnitPlansRacesView;
import sfgamedataeditor.views.main.modules.items.weapons.WeaponsTypesListView;
import sfgamedataeditor.views.main.modules.items.workersrunes.WorkersRuneRacesView;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

import java.util.ArrayList;
import java.util.List;

public class ItemTypesView extends AbstractModulesView {

    public ItemTypesView() {
        super(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "itemTypes"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fillSubViewsMappings() {
        List<SubViewPanelTuple> mappings = new ArrayList<SubViewPanelTuple>() {{
            add(createTuple("items.weapons", WeaponsTypesListView.class));
            add(createTuple("items.armor", ArmorTypeListView.class));
            add(createTuple("items.scrolls.and.spells", SpellScrollsListView.class));
            add(createTuple( "items.workers.runes", WorkersRuneRacesView.class));
            add(createTuple( "items.heroes.runes", HeroesRunesListView.class));
            add(createTuple("items.buildingPlans", UnitPlansRacesView.class));
            add(createTuple("items.unitPlans", BuildingPlansRacesView.class));
            add(createTuple("items.miscellaneous", MiscellaneousListView.class));
        }};

        addMappings(mappings);
    }

    private SubViewPanelTuple createTuple(String i18nKey, Class<? extends PresentableView> viewClass) {
        return new SubViewPanelTuple(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, i18nKey), viewClass);
    }

    @Override
    public Class<? extends AbstractPresenter> getPresenterClass() {
        return ItemTypesPresenter.class;
    }
}
