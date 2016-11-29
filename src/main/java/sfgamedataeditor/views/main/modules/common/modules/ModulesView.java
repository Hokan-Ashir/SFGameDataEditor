package sfgamedataeditor.views.main.modules.common.modules;

import sfgamedataeditor.mvc.objects.AbstractController;
import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.common.AbstractModulesView;
import sfgamedataeditor.views.main.modules.buildings.BuildingRacesView;
import sfgamedataeditor.views.main.modules.creatures.races.CreaturesRacesView;
import sfgamedataeditor.views.main.modules.items.ItemTypesView;
import sfgamedataeditor.views.main.modules.merchants.MerchantLocationsView;
import sfgamedataeditor.views.main.modules.skills.schools.SkillSchoolsView;
import sfgamedataeditor.views.main.modules.spells.schools.SpellSchoolsView;

public class ModulesView extends AbstractModulesView {

    public ModulesView() {
        super(I18N.INSTANCE.getMessage("modulesList"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fillComboBoxMapping() {
        addMapping(I18N.INSTANCE.getMessage("skills"), SkillSchoolsView.class);
        addMapping(I18N.INSTANCE.getMessage("spells"), SpellSchoolsView.class);
        addMapping(I18N.INSTANCE.getMessage("merchants"), MerchantLocationsView.class);
        addMapping(I18N.INSTANCE.getMessage("buildings"), BuildingRacesView.class);
        addMapping(I18N.INSTANCE.getMessage("creatures"), CreaturesRacesView.class);
        addMapping(I18N.INSTANCE.getMessage("items"), ItemTypesView.class);
    }

    @Override
    public Class<? extends AbstractController> getControllerClass() {
        return ModulesController.class;
    }
}
