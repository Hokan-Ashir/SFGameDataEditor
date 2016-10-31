package sfgamedataeditor.views.main.modules.common.modules;

import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.common.AbstractModulesView;
import sfgamedataeditor.views.main.modules.buildings.BuildingRacesView;
import sfgamedataeditor.views.main.modules.items.ItemTypesView;
import sfgamedataeditor.views.main.modules.merchants.MerchantLocationsView;
import sfgamedataeditor.views.main.modules.skills.schools.SkillSchoolsView;
import sfgamedataeditor.views.main.modules.spells.schools.SpellSchoolsView;
import sfgamedataeditor.views.main.modules.units.UnitsRacesView;

public class ModulesView extends AbstractModulesView {

    public ModulesView() {
        super(I18N.INSTANCE.getMessage("modulesList"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fillComboBoxMapping() {
        addSkillsMapping();
        addSpellsMapping();
        addMerchantsMapping();
        addBuildingsMapping();
        addUnitsMapping();
        addItemsMapping();
    }

    private void addSkillsMapping() {
        addMapping(I18N.INSTANCE.getMessage("skills"), SkillSchoolsView.class);
    }

    private void addSpellsMapping() {
        addMapping(I18N.INSTANCE.getMessage("spells"), SpellSchoolsView.class);
    }

    private void addMerchantsMapping() {
        addMapping(I18N.INSTANCE.getMessage("merchants"), MerchantLocationsView.class);
    }

    private void addBuildingsMapping() {
        addMapping(I18N.INSTANCE.getMessage("buildings"), BuildingRacesView.class);
    }

    private void addUnitsMapping() {
        addMapping(I18N.INSTANCE.getMessage("units"), UnitsRacesView.class);
    }

    private void addItemsMapping() {
        addMapping(I18N.INSTANCE.getMessage("items"), ItemTypesView.class);
    }
}
