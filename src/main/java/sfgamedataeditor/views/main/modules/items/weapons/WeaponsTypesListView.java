package sfgamedataeditor.views.main.modules.items.weapons;

import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.common.AbstractModulesView;
import sfgamedataeditor.views.main.modules.items.ItemTypesView;

public class WeaponsTypesListView extends AbstractModulesView<ItemTypesView> {

    public WeaponsTypesListView(ItemTypesView parentView) {
        super(parentView, I18N.INSTANCE.getMessage("weaponTypes"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fillComboBoxMapping() {

    }

}
