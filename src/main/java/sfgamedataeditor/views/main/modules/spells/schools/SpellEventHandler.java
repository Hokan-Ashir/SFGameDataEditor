package sfgamedataeditor.views.main.modules.spells.schools;

import sfgamedataeditor.ViewRegister;
import sfgamedataeditor.events.EventHandler;
import sfgamedataeditor.views.main.modules.spells.schools.spells.SpellViewMetaEvent;
import sfgamedataeditor.views.main.modules.spells.schools.spells.parameters.SpellParameterViewMetaEvent;

public class SpellEventHandler {

    @EventHandler
    public void onShowSpellsView(SpellViewMetaEvent event) {
        ViewRegister.INSTANCE.process(event);
    }

    @EventHandler
    public void onShowSpellParameterView(SpellParameterViewMetaEvent event) {
        ViewRegister.INSTANCE.process(event);
    }
}
