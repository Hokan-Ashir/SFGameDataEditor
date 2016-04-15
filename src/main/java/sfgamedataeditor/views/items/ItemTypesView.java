package sfgamedataeditor.views.items;

import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.ModulesView;
import sfgamedataeditor.views.common.AbstractModulesView;
import sfgamedataeditor.views.items.armor.ArmorListView;
import sfgamedataeditor.views.items.misc.MiscListView;
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
        getComboBoxMapping().put(I18N.INSTANCE.getMessage(""), WeaponsListView.class);
        getComboBoxMapping().put(I18N.INSTANCE.getMessage(""), ArmorListView.class);
        getComboBoxMapping().put(I18N.INSTANCE.getMessage(""), SpellScrollsListView.class);
        getComboBoxMapping().put(I18N.INSTANCE.getMessage(""), RuneRacesListView.class);
        getComboBoxMapping().put(I18N.INSTANCE.getMessage(""), MiscListView.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void show() {

    }
}
