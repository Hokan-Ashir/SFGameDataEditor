package sfgamedataeditor.views.main.modules.items.spellscrolls;

import sfgamedataeditor.datamapping.Mappings;
import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.views.common.AbstractModulesView;
import sfgamedataeditor.views.common.NotImplementedView;
import sfgamedataeditor.views.common.ShowNotImplementedViewEvent;
import sfgamedataeditor.views.main.modules.items.ItemTypesView;

public class SpellScrollsListView extends AbstractModulesView<ItemTypesView> {

    public SpellScrollsListView(ItemTypesView parentView) {
        super(parentView);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fillComboBoxMapping() {
        ClassTuple tuple = new ClassTuple(NotImplementedView.class, this);
        ShowNotImplementedViewEvent event = new ShowNotImplementedViewEvent(tuple);
        for (String s : Mappings.INSTANCE.SPELL_SCHOOL_MAP.keySet()) {
            addMapping(s, event);
        }
    }

}
