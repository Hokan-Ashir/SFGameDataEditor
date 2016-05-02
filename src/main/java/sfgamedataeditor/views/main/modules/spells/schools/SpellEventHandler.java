package sfgamedataeditor.views.main.modules.spells.schools;

import sfgamedataeditor.events.EventHandler;
import sfgamedataeditor.events.processing.EventProcessor;
import sfgamedataeditor.views.main.modules.spells.schools.spells.SpellViewMetaEvent;
import sfgamedataeditor.views.main.modules.spells.schools.spells.parameters.SpellParameterViewMetaEvent;

public class SpellEventHandler {

    @EventHandler
    public void onShowSpellsView(SpellViewMetaEvent event) {
        EventProcessor.INSTANCE.process(event);
    }

    @EventHandler
    public void onShowSpellParameterView(SpellParameterViewMetaEvent event) {
        EventProcessor.INSTANCE.process(event);
    }
}
