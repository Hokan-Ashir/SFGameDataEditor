package sfgamedataeditor.views.main.modules.items.spellscrolls;

import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.ShowViewEvent;
import sfgamedataeditor.views.main.modules.items.ItemTypesView;

public class ShowSpellScrollsViewEvent extends ShowViewEvent<SpellScrollsListView, ItemTypesView, Object> {

    public ShowSpellScrollsViewEvent(ClassTuple<SpellScrollsListView, ItemTypesView> tuple) {
        super(tuple);
    }
}
