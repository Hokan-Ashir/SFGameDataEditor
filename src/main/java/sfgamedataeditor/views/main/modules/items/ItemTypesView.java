package sfgamedataeditor.views.main.modules.items;

import sfgamedataeditor.events.EventHandlerRegister;
import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.common.AbstractModulesView;
import sfgamedataeditor.views.main.modules.common.modules.ModulesView;
import sfgamedataeditor.views.main.modules.items.armor.ArmorTypesMetaEvent;
import sfgamedataeditor.views.main.modules.items.buildingplans.BuildingPlansMetaEvent;
import sfgamedataeditor.views.main.modules.items.miscellaneous.MiscellaneousMetaEvent;
import sfgamedataeditor.views.main.modules.items.runes.RuneRacesMetaEvent;
import sfgamedataeditor.views.main.modules.items.spellscrolls.SpellScrollsMetaEvent;
import sfgamedataeditor.views.main.modules.items.weapons.WeaponTypesMetaEvent;

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
        WeaponTypesMetaEvent event = new WeaponTypesMetaEvent();
        addMapping(I18N.INSTANCE.getMessage("items.weapons"), event);
    }

    private void addArmorListMapping() {
        ArmorTypesMetaEvent event = new ArmorTypesMetaEvent();
        addMapping(I18N.INSTANCE.getMessage("items.armor"), event);
    }

    private void addSpellScrollsMapping() {
        SpellScrollsMetaEvent event = new SpellScrollsMetaEvent();
        addMapping(I18N.INSTANCE.getMessage("items.spellScrolls"), event);
    }

    private void addRuneListMapping() {
        RuneRacesMetaEvent event = new RuneRacesMetaEvent();
        addMapping(I18N.INSTANCE.getMessage("items.runes"), event);
    }

    private void addBuildingPlansMapping() {
        BuildingPlansMetaEvent event = new BuildingPlansMetaEvent();
        addMapping(I18N.INSTANCE.getMessage("items.buildingPlans"), event);
    }

    private void addMiscellaneousMapping() {
        MiscellaneousMetaEvent event = new MiscellaneousMetaEvent();
        addMapping(I18N.INSTANCE.getMessage("items.miscellaneous"), event);
    }

}
