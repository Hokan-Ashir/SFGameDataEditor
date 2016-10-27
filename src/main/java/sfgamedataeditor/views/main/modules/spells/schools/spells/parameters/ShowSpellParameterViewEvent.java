package sfgamedataeditor.views.main.modules.spells.schools.spells.parameters;

import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.types.ShowViewEvent;
import sfgamedataeditor.views.main.MainView;

public class ShowSpellParameterViewEvent extends ShowViewEvent<SpellParameterView, MainView, SpellParameterEventParameter> {

    public ShowSpellParameterViewEvent(ClassTuple<SpellParameterView, MainView> tuple) {
        super(tuple);
    }
}
