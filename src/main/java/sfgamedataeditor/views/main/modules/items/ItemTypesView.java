package sfgamedataeditor.views.main.modules.items;

import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.common.AbstractModulesView;
import sfgamedataeditor.views.main.modules.items.armor.ArmorTypeListView;
import sfgamedataeditor.views.main.modules.items.buildingplans.BuildingPlansListView;
import sfgamedataeditor.views.main.modules.items.miscellaneous.MiscellaneousListView;
import sfgamedataeditor.views.main.modules.items.runes.RuneRacesListView;
import sfgamedataeditor.views.main.modules.items.spellscrolls.SpellScrollsListView;
import sfgamedataeditor.views.main.modules.items.weapons.WeaponsTypesListView;

public class ItemTypesView extends AbstractModulesView {

    public ItemTypesView() {
        super(I18N.INSTANCE.getMessage("itemTypes"));
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
        addMapping(I18N.INSTANCE.getMessage("items.weapons"), WeaponsTypesListView.class);
    }

    private void addArmorListMapping() {
        addMapping(I18N.INSTANCE.getMessage("items.armor"), ArmorTypeListView.class);
    }

    private void addSpellScrollsMapping() {
        addMapping(I18N.INSTANCE.getMessage("items.spellScrolls"), SpellScrollsListView.class);
    }

    private void addRuneListMapping() {
        addMapping(I18N.INSTANCE.getMessage("items.runes"), RuneRacesListView.class);
    }

    private void addBuildingPlansMapping() {
        addMapping(I18N.INSTANCE.getMessage("items.buildingPlans"), BuildingPlansListView.class);
    }

    private void addMiscellaneousMapping() {
        addMapping(I18N.INSTANCE.getMessage("items.miscellaneous"), MiscellaneousListView.class);
    }
}
