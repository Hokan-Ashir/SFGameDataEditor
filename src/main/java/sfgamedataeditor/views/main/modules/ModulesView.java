package sfgamedataeditor.views.main.modules;

import sfgamedataeditor.mvc.objects.AbstractController;
import sfgamedataeditor.views.common.AbstractModulesView;
import sfgamedataeditor.views.main.modules.buildings.BuildingRacesView;
import sfgamedataeditor.views.main.modules.creatures.races.CreaturesRacesView;
import sfgamedataeditor.views.main.modules.items.ItemTypesView;
import sfgamedataeditor.views.main.modules.merchants.MerchantLocationsView;
import sfgamedataeditor.views.main.modules.skills.schools.SkillSchoolsView;
import sfgamedataeditor.views.main.modules.spells.schools.SpellSchoolsView;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

public class ModulesView extends AbstractModulesView {

    public ModulesView() {
        super(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "modulesList"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fillComboBoxMapping() {
        addMapping(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "skills"), SkillSchoolsView.class);
        addMapping(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "spells"), SpellSchoolsView.class);
        addMapping(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "merchants"), MerchantLocationsView.class);
        addMapping(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "buildings"), BuildingRacesView.class);
        addMapping(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "creatures"), CreaturesRacesView.class);
        addMapping(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "items"), ItemTypesView.class);
    }

    @Override
    public Class<? extends AbstractController> getControllerClass() {
        return ModulesController.class;
    }
}
