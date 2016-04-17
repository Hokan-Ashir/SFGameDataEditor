package sfgamedataeditor.views.main.modules.spells.schools.spells.parameters;

import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.ShowViewEvent;
import sfgamedataeditor.views.main.modules.spells.schools.spells.SpellsView;

public class ShowSpellParameterViewEvent extends ShowViewEvent<SpellParameterView, SpellsView, SpellParameterEventParameter> {

    public ShowSpellParameterViewEvent(ClassTuple<SpellParameterView, SpellsView> tuple) {
        super(tuple);
    }
}
