package sfgamedataeditor.views.main.modules.spells.schools;

import sfgamedataeditor.ViewRegister;
import sfgamedataeditor.events.EventHandler;
import sfgamedataeditor.views.main.modules.spells.schools.spells.ShowSpellsViewEvent;
import sfgamedataeditor.views.main.modules.spells.schools.spells.parameters.ShowSpellParameterViewEvent;

public class SpellEventHandler {

    @EventHandler
    public void onShowSpellsView(ShowSpellsViewEvent event) {
        ViewRegister.INSTANCE.process(event);
    }

    @EventHandler
    public void onShowSpellParameterView(ShowSpellParameterViewEvent event) {
        ViewRegister.INSTANCE.process(event);
    }
}
