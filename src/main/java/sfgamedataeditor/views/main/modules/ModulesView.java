package sfgamedataeditor.views.main.modules;

import sfgamedataeditor.mvc.objects.AbstractPresenter;
import sfgamedataeditor.mvc.objects.PresentableView;
import sfgamedataeditor.views.common.AbstractModulesView;
import sfgamedataeditor.views.main.modules.buildings.BuildingRacesView;
import sfgamedataeditor.views.main.modules.creatures.races.CreaturesRacesView;
import sfgamedataeditor.views.main.modules.items.ItemTypesView;
import sfgamedataeditor.views.main.modules.merchants.MerchantsView;
import sfgamedataeditor.views.main.modules.skills.schools.SkillSchoolsView;
import sfgamedataeditor.views.main.modules.spells.schools.SpellSchoolsView;
import sfgamedataeditor.views.main.modules.units.races.UnitRacesView;
import sfgamedataeditor.views.utility.Pair;
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
    protected void fillSubViewsMappings() {
        List<Pair<String, Class<? extends PresentableView>>> mappings = new ArrayList<>();
        mappings.add(new Pair<String, Class<? extends PresentableView>>(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "skills"), SkillSchoolsView.class));
        mappings.add(new Pair<String, Class<? extends PresentableView>>(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "spells"), SpellSchoolsView.class));
        mappings.add(new Pair<String, Class<? extends PresentableView>>(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "merchants"), MerchantsView.class));
        mappings.add(new Pair<String, Class<? extends PresentableView>>(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "buildings"), BuildingRacesView.class));
        mappings.add(new Pair<String, Class<? extends PresentableView>>(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "creatures"), CreaturesRacesView.class));
        mappings.add(new Pair<String, Class<? extends PresentableView>>(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "items"), ItemTypesView.class));
        mappings.add(new Pair<String, Class<? extends PresentableView>>(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "units"), UnitRacesView.class));

        addMappings(mappings);
    }

    @Override
    public Class<? extends AbstractPresenter> getPresenterClass() {
        return ModulesPresenter.class;
    }
}
