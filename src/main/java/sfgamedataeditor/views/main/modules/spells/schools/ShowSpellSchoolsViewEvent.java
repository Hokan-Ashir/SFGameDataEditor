package sfgamedataeditor.views.main.modules.spells.schools;

import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.ShowViewEvent;
import sfgamedataeditor.views.main.modules.common.modules.ModulesView;

public class ShowSpellSchoolsViewEvent extends ShowViewEvent<SpellSchoolsView, ModulesView, Object> {

    public ShowSpellSchoolsViewEvent(ClassTuple<SpellSchoolsView, ModulesView> tuple) {
        super(tuple);
    }
}
