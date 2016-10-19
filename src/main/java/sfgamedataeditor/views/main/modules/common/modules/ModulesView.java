package sfgamedataeditor.views.main.modules.common.modules;

import sfgamedataeditor.events.EventHandlerRegister;
import sfgamedataeditor.events.PostProcess;
import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.common.AbstractModulesView;
import sfgamedataeditor.views.main.MainView;
import sfgamedataeditor.views.main.modules.buildings.BuildingRacesMetaEvent;
import sfgamedataeditor.views.main.modules.items.ItemTypesMetaEvent;
import sfgamedataeditor.views.main.modules.merchants.MerchantLocationsMetaEvent;
import sfgamedataeditor.views.main.modules.skills.schools.SkillSchoolsMetaEvent;
import sfgamedataeditor.views.main.modules.spells.schools.SpellSchoolsMetaEvent;
import sfgamedataeditor.views.main.modules.units.UnitRacesMetaEvent;

public class ModulesView extends AbstractModulesView<MainView> {

    public ModulesView(MainView parentView) {
        super(parentView, I18N.INSTANCE.getMessage("modulesList"));
        EventHandlerRegister.INSTANCE.addEventHandler(new ModulesEventHandler());
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
        SkillSchoolsMetaEvent event = new SkillSchoolsMetaEvent();
        addMapping(I18N.INSTANCE.getMessage("skills"), event);
    }

    private void addSpellsMapping() {
        SpellSchoolsMetaEvent event = new SpellSchoolsMetaEvent();
        addMapping(I18N.INSTANCE.getMessage("spells"), event);
    }

    private void addMerchantsMapping() {
        MerchantLocationsMetaEvent event = new MerchantLocationsMetaEvent();
        addMapping(I18N.INSTANCE.getMessage("merchants"), event);
    }

    private void addBuildingsMapping() {
        BuildingRacesMetaEvent event = new BuildingRacesMetaEvent();
        addMapping(I18N.INSTANCE.getMessage("buildings"), event);
    }

    private void addUnitsMapping() {
        UnitRacesMetaEvent event = new UnitRacesMetaEvent();
        addMapping(I18N.INSTANCE.getMessage("units"), event);
    }

    private void addItemsMapping() {
        ItemTypesMetaEvent event = new ItemTypesMetaEvent();
        addMapping(I18N.INSTANCE.getMessage("items"), event);
    }
}
