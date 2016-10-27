package sfgamedataeditor.views.main.modules.items.armor;

import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.common.AbstractModulesView;
import sfgamedataeditor.views.main.MainView;

public class ArmorTypeListView extends AbstractModulesView<MainView, ArmorTypesMetaEvent> {

    public ArmorTypeListView(MainView parentView) {
        super(parentView, I18N.INSTANCE.getMessage("armorTypes"));
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
    public Class<ArmorTypesMetaEvent> getMetaEventClass() {
        return ArmorTypesMetaEvent.class;
    }
}
