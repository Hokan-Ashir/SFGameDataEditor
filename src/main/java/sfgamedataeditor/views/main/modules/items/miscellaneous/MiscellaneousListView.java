package sfgamedataeditor.views.main.modules.items.miscellaneous;

import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.common.AbstractModulesView;
import sfgamedataeditor.views.common.AbstractView;
import sfgamedataeditor.views.main.MainView;
import sfgamedataeditor.views.main.modules.items.ItemTypesView;

public class MiscellaneousListView extends AbstractModulesView<MainView, MiscellaneousMetaEvent> {

    public MiscellaneousListView(MainView parentView) {
        super(parentView, I18N.INSTANCE.getMessage("miscellaneousTypes"));
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
    public Class<MiscellaneousMetaEvent> getMetaEventClass() {
        return MiscellaneousMetaEvent.class;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<? extends AbstractView> getParentHierarchyClass() {
        return ItemTypesView.class;
    }
}
