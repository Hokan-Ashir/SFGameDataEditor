package sfgamedataeditor.views.main.modules.spells.schools.spells;

import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.types.ShowViewEvent;
import sfgamedataeditor.views.main.modules.spells.schools.SpellSchoolsView;

public class ShowSpellsViewEvent extends ShowViewEvent<SpellsView, SpellSchoolsView, Object> {

    public ShowSpellsViewEvent(ClassTuple<SpellsView, SpellSchoolsView> tuple) {
        super(tuple);
    }
}
