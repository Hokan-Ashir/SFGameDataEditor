package sfgamedataeditor.views.main.modules.items;

import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.mvc.objects.PresentableView;
import sfgamedataeditor.views.common.AbstractModulesView;
import sfgamedataeditor.views.main.modules.items.armor.ArmorTypeListView;
import sfgamedataeditor.views.main.modules.items.buildingplans.BuildingPlansRacesView;
import sfgamedataeditor.views.main.modules.items.miscellaneous.MiscellaneousListView;
import sfgamedataeditor.views.main.modules.items.spellscrolls.schools.SpellScrollsListView;
import sfgamedataeditor.views.main.modules.items.unitplans.UnitPlansRacesView;
import sfgamedataeditor.views.main.modules.items.weapons.WeaponsTypesListView;
import sfgamedataeditor.views.utility.Pair;
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
    protected void fillSubViewsMappings() {
        List<Pair<String, Class<? extends PresentableView>>> mappings = new ArrayList<>();
        mappings.add(new Pair<String, Class<? extends PresentableView>>(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "items.weapons"), WeaponsTypesListView.class));
        mappings.add(new Pair<String, Class<? extends PresentableView>>(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "items.armor"), ArmorTypeListView.class));
        mappings.add(new Pair<String, Class<? extends PresentableView>>(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "items.scrolls"), SpellScrollsListView.class));
//        mappings.add(new Pair<String, Class<? extends PresentableView>>(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "items.runes"), RuneRacesListView.class);
        mappings.add(new Pair<String, Class<? extends PresentableView>>(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "items.buildingPlans"), BuildingPlansRacesView.class));
        mappings.add(new Pair<String, Class<? extends PresentableView>>(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "items.unitPlans"), UnitPlansRacesView.class));
        mappings.add(new Pair<String, Class<? extends PresentableView>>(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "items.miscellaneous"), MiscellaneousListView.class));

        addMappings(mappings);
    }

    @Override
    public Class<? extends AbstractPresenter> getPresenterClass() {
        return ItemTypesPresenter.class;
    }
}
