package sfgamedataeditor.views.main.modules.items.spellscrolls;

import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.common.AbstractModulesView;
import sfgamedataeditor.views.common.AbstractView;
import sfgamedataeditor.views.main.MainView;
import sfgamedataeditor.views.main.modules.items.ItemTypesView;

public class SpellScrollsListView extends AbstractModulesView<MainView, SpellScrollsMetaEvent> {

    public SpellScrollsListView(MainView parentView) {
        super(parentView, I18N.INSTANCE.getMessage("spellSchools"));
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
    public Class<SpellScrollsMetaEvent> getMetaEventClass() {
        return SpellScrollsMetaEvent.class;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<? extends AbstractView> getParentHierarchyClass() {
        return ItemTypesView.class;
    }
}
