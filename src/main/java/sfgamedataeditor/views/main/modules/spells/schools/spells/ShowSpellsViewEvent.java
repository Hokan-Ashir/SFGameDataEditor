package sfgamedataeditor.views.main.modules.spells.schools.spells;

import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.types.ShowViewEvent;
import sfgamedataeditor.views.main.MainView;

public class ShowSpellsViewEvent extends ShowViewEvent<SpellsView, MainView, Object> {

    public ShowSpellsViewEvent(ClassTuple<SpellsView, MainView> tuple) {
        super(tuple);
    }
}
