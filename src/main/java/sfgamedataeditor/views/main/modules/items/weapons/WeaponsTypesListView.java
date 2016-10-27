package sfgamedataeditor.views.main.modules.items.weapons;

import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.common.AbstractModulesView;
import sfgamedataeditor.views.main.MainView;

public class WeaponsTypesListView extends AbstractModulesView<MainView, WeaponTypesMetaEvent> {

    public WeaponsTypesListView(MainView parentView) {
        super(parentView, I18N.INSTANCE.getMessage("weaponTypes"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fillComboBoxMapping() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<WeaponTypesMetaEvent> getMetaEventClass() {
        return WeaponTypesMetaEvent.class;
    }
}
