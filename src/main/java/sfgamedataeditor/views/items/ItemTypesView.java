package sfgamedataeditor.views.items;

import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.ModulesView;
import sfgamedataeditor.views.common.AbstractModulesView;
import sfgamedataeditor.views.items.armor.ArmorListView;
import sfgamedataeditor.views.items.buildingscrolls.BuildingPlansListView;
import sfgamedataeditor.views.items.miscellaneous.MiscellaneousListView;
import sfgamedataeditor.views.items.runes.RuneRacesListView;
import sfgamedataeditor.views.items.spellscrolls.SpellScrollsListView;
import sfgamedataeditor.views.items.weapons.WeaponsListView;

public class ItemTypesView extends AbstractModulesView<ModulesView> {

    public ItemTypesView(ModulesView parentView) {
        super(parentView);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fillComboBoxMapping() {
        getComboBoxMapping().put(I18N.INSTANCE.getMessage("items.weapons"), WeaponsListView.class);
        getComboBoxMapping().put(I18N.INSTANCE.getMessage("items.armor"), ArmorListView.class);
        getComboBoxMapping().put(I18N.INSTANCE.getMessage("items.spellScrolls"), SpellScrollsListView.class);
        getComboBoxMapping().put(I18N.INSTANCE.getMessage("items.runes"), RuneRacesListView.class);
        getComboBoxMapping().put(I18N.INSTANCE.getMessage("items.buildingPlans"), BuildingPlansListView.class);
        getComboBoxMapping().put(I18N.INSTANCE.getMessage("items.miscellaneous"), MiscellaneousListView.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void show() {

    }
}
