package sfgamedataeditor.views.main.modules.items;

import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.EventHandlerRegister;
import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.common.AbstractModulesView;
import sfgamedataeditor.views.main.modules.ModulesView;
import sfgamedataeditor.views.main.modules.items.armor.ArmorListView;
import sfgamedataeditor.views.main.modules.items.buildingplans.BuildingPlansListView;
import sfgamedataeditor.views.main.modules.items.miscellaneous.MiscellaneousListView;
import sfgamedataeditor.views.main.modules.items.runes.RuneRacesListView;
import sfgamedataeditor.views.main.modules.items.spellscrolls.SpellScrollsListView;
import sfgamedataeditor.views.main.modules.items.weapons.ShowWeaponListViewEvent;
import sfgamedataeditor.views.main.modules.items.weapons.WeaponsListView;

public class ItemTypesView extends AbstractModulesView<ModulesView> {

    public ItemTypesView(ModulesView parentView) {
        super(parentView);
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
        ClassTuple tuple = new ClassTuple<>(WeaponsListView.class, this);
        ShowWeaponListViewEvent event = new ShowWeaponListViewEvent(tuple);
        addMapping(I18N.INSTANCE.getMessage("items.weapons"), event);
    }

    private void addArmorListMapping() {
        ClassTuple tuple = new ClassTuple<>(ArmorListView.class, this);
        ShowWeaponListViewEvent event = new ShowWeaponListViewEvent(tuple);
        addMapping(I18N.INSTANCE.getMessage("items.armor"), event);
    }

    private void addSpellScrollsMapping() {
        ClassTuple tuple = new ClassTuple<>(SpellScrollsListView.class, this);
        ShowWeaponListViewEvent event = new ShowWeaponListViewEvent(tuple);
        addMapping(I18N.INSTANCE.getMessage("items.spellScrolls"), event);
    }

    private void addRuneListMapping() {
        ClassTuple tuple = new ClassTuple<>(RuneRacesListView.class, this);
        ShowWeaponListViewEvent event = new ShowWeaponListViewEvent(tuple);
        addMapping(I18N.INSTANCE.getMessage("items.runes"), event);
    }

    private void addBuildingPlansMapping() {
        ClassTuple tuple = new ClassTuple<>(BuildingPlansListView.class, this);
        ShowWeaponListViewEvent event = new ShowWeaponListViewEvent(tuple);
        addMapping(I18N.INSTANCE.getMessage("items.buildingPlans"), event);
    }

    private void addMiscellaneousMapping() {
        ClassTuple tuple = new ClassTuple<>(MiscellaneousListView.class, this);
        ShowWeaponListViewEvent event = new ShowWeaponListViewEvent(tuple);
        addMapping(I18N.INSTANCE.getMessage("items.miscellaneous"), event);
    }

}
