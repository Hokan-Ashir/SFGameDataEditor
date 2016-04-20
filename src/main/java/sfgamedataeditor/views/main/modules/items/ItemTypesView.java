package sfgamedataeditor.views.main.modules.items;

import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.EventHandlerRegister;
import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.common.AbstractModulesView;
import sfgamedataeditor.views.main.modules.common.modules.ModulesView;
import sfgamedataeditor.views.main.modules.items.armor.ArmorTypeListView;
import sfgamedataeditor.views.main.modules.items.buildingplans.BuildingPlansListView;
import sfgamedataeditor.views.main.modules.items.miscellaneous.MiscellaneousListView;
import sfgamedataeditor.views.main.modules.items.runes.RuneRacesListView;
import sfgamedataeditor.views.main.modules.items.spellscrolls.SpellScrollsListView;
import sfgamedataeditor.views.main.modules.items.weapons.ShowWeaponTypesListViewEvent;
import sfgamedataeditor.views.main.modules.items.weapons.WeaponsTypesListView;

public class ItemTypesView extends AbstractModulesView<ModulesView> {

    public ItemTypesView(ModulesView parentView) {
        super(parentView, I18N.INSTANCE.getMessage("itemTypes"));
        EventHandlerRegister.INSTANCE.addEventHandler(new ItemTypesEventHandler());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fillComboBoxMapping() {
        addWeaponsListMapping();
        addArmorListMapping();
        addSpellScrollsMapping();
        addRuneListMapping();
        addBuildingPlansMapping();
        addMiscellaneousMapping();
    }

    private void addWeaponsListMapping() {
        ClassTuple tuple = new ClassTuple<>(WeaponsTypesListView.class, this);
        ShowWeaponTypesListViewEvent event = new ShowWeaponTypesListViewEvent(tuple);
        addMapping(I18N.INSTANCE.getMessage("items.weapons"), event);
    }

    private void addArmorListMapping() {
        ClassTuple tuple = new ClassTuple<>(ArmorTypeListView.class, this);
        ShowWeaponTypesListViewEvent event = new ShowWeaponTypesListViewEvent(tuple);
        addMapping(I18N.INSTANCE.getMessage("items.armor"), event);
    }

    private void addSpellScrollsMapping() {
        ClassTuple tuple = new ClassTuple<>(SpellScrollsListView.class, this);
        ShowWeaponTypesListViewEvent event = new ShowWeaponTypesListViewEvent(tuple);
        addMapping(I18N.INSTANCE.getMessage("items.spellScrolls"), event);
    }

    private void addRuneListMapping() {
        ClassTuple tuple = new ClassTuple<>(RuneRacesListView.class, this);
        ShowWeaponTypesListViewEvent event = new ShowWeaponTypesListViewEvent(tuple);
        addMapping(I18N.INSTANCE.getMessage("items.runes"), event);
    }

    private void addBuildingPlansMapping() {
        ClassTuple tuple = new ClassTuple<>(BuildingPlansListView.class, this);
        ShowWeaponTypesListViewEvent event = new ShowWeaponTypesListViewEvent(tuple);
        addMapping(I18N.INSTANCE.getMessage("items.buildingPlans"), event);
    }

    private void addMiscellaneousMapping() {
        ClassTuple tuple = new ClassTuple<>(MiscellaneousListView.class, this);
        ShowWeaponTypesListViewEvent event = new ShowWeaponTypesListViewEvent(tuple);
        addMapping(I18N.INSTANCE.getMessage("items.miscellaneous"), event);
    }

}
