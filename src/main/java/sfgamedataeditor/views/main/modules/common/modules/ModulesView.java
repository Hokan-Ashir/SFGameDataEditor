package sfgamedataeditor.views.main.modules.common.modules;

import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.EventHandlerRegister;
import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.common.AbstractModulesView;
import sfgamedataeditor.views.main.MainView;
import sfgamedataeditor.views.main.modules.buildings.BuildingRacesView;
import sfgamedataeditor.views.main.modules.buildings.ShowBuildingRacesViewEvent;
import sfgamedataeditor.views.main.modules.items.ItemTypesView;
import sfgamedataeditor.views.main.modules.items.ShowItemTypesViewEvent;
import sfgamedataeditor.views.main.modules.merchants.MerchantLocationsView;
import sfgamedataeditor.views.main.modules.merchants.ShowMerchantsLocationsViewEvent;
import sfgamedataeditor.views.main.modules.skills.schools.ShowSkillSchoolsViewEvent;
import sfgamedataeditor.views.main.modules.skills.schools.SkillSchoolsView;
import sfgamedataeditor.views.main.modules.spells.schools.ShowSpellSchoolsViewEvent;
import sfgamedataeditor.views.main.modules.spells.schools.SpellSchoolsView;
import sfgamedataeditor.views.main.modules.units.ShowUnitsRacesViewEvent;
import sfgamedataeditor.views.main.modules.units.UnitsRacesView;

public class ModulesView extends AbstractModulesView<MainView> {

    public ModulesView(MainView parentView) {
        super(parentView, I18N.INSTANCE.getMessage("modulesList"));
        EventHandlerRegister.INSTANCE.addEventHandler(new ModulesEventHandler());
        selectFirstComboBoxItem();
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
        ClassTuple tuple = new ClassTuple<>(SkillSchoolsView.class, this);
        ShowSkillSchoolsViewEvent event = new ShowSkillSchoolsViewEvent(tuple);
        addMapping(I18N.INSTANCE.getMessage("skills"), event);
    }

    private void addSpellsMapping() {
        ClassTuple tuple = new ClassTuple<>(SpellSchoolsView.class, this);
        ShowSpellSchoolsViewEvent event = new ShowSpellSchoolsViewEvent(tuple);
        addMapping(I18N.INSTANCE.getMessage("spells"), event);
    }

    private void addMerchantsMapping() {
        ClassTuple tuple = new ClassTuple<>(MerchantLocationsView.class, this);
        ShowMerchantsLocationsViewEvent event = new ShowMerchantsLocationsViewEvent(tuple);
        addMapping(I18N.INSTANCE.getMessage("merchants"), event);
    }

    private void addBuildingsMapping() {
        ClassTuple tuple = new ClassTuple<>(BuildingRacesView.class, this);
        ShowBuildingRacesViewEvent event = new ShowBuildingRacesViewEvent(tuple);
        addMapping(I18N.INSTANCE.getMessage("buildings"), event);
    }

    private void addUnitsMapping() {
        ClassTuple tuple = new ClassTuple<>(UnitsRacesView.class, this);
        ShowUnitsRacesViewEvent event = new ShowUnitsRacesViewEvent(tuple);
        addMapping(I18N.INSTANCE.getMessage("units"), event);
    }

    private void addItemsMapping() {
        ClassTuple tuple = new ClassTuple<>(ItemTypesView.class, this);
        ShowItemTypesViewEvent event = new ShowItemTypesViewEvent(tuple);
        addMapping(I18N.INSTANCE.getMessage("items"), event);
    }
}
