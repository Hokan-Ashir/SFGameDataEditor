package sfgamedataeditor.views.main.modules.items.spellscrolls;

import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.common.AbstractModulesView;
import sfgamedataeditor.views.main.modules.items.ItemTypesView;

public class SpellScrollsListView extends AbstractModulesView<ItemTypesView> {

    public SpellScrollsListView(ItemTypesView parentView) {
        super(parentView, I18N.INSTANCE.getMessage("spellSchools"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fillComboBoxMapping() {

    }

}
