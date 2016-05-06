package sfgamedataeditor.views.main.modules.spells.schools.spells.parameters;

import sfgamedataeditor.events.EventCreator;
import sfgamedataeditor.events.types.AbstractMetaEvent;
import sfgamedataeditor.events.types.SetModuleNameEvent;
import sfgamedataeditor.views.common.levelable.LevelableView;
import sfgamedataeditor.views.common.levelable.ShowLevelableViewEvent;
import sfgamedataeditor.views.main.modules.common.modules.ModulesView;
import sfgamedataeditor.views.main.modules.spells.schools.SpellSchoolsView;
import sfgamedataeditor.views.main.modules.spells.schools.spells.SpellsView;

public class SpellParameterViewMetaEvent extends AbstractMetaEvent {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addEvents() {
        SetModuleNameEvent moduleNameEvent = new SetModuleNameEvent(SpellsView.class, SpellSchoolsView.class);
        ShowLevelableViewEvent levelableViewEvent = EventCreator.createEvent(LevelableView.class, SpellsView.class, ShowLevelableViewEvent.class);
        ShowSpellParameterViewEvent event = EventCreator.createEvent(SpellParameterView.class, SpellsView.class, ShowSpellParameterViewEvent.class);
        addEvents(moduleNameEvent, levelableViewEvent, event);
    }
}
