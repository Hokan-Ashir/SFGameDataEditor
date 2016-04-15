package sfgamedataeditor.views.items.spellscrolls;

import sfgamedataeditor.datamapping.Mappings;
import sfgamedataeditor.views.common.AbstractModulesView;
import sfgamedataeditor.views.common.NotImplementedView;
import sfgamedataeditor.views.items.ItemTypesView;

public class SpellScrollsListView extends AbstractModulesView<ItemTypesView> {

    public SpellScrollsListView(ItemTypesView parentView) {
        super(parentView);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fillComboBoxMapping() {
        for (String s : Mappings.INSTANCE.SPELL_SCHOOL_MAP.keySet()) {
            getComboBoxMapping().put(s, NotImplementedView.class);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void show() {

    }
}
