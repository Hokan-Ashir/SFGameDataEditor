package sfgamedataeditor.views.main.modules.units;

import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.EventCreator;
import sfgamedataeditor.events.types.AbstractMetaEvent;
import sfgamedataeditor.events.types.ClearViewEvent;
import sfgamedataeditor.events.types.SetModuleNameEvent;
import sfgamedataeditor.events.types.ShowViewEvent;
import sfgamedataeditor.views.main.MainView;
import sfgamedataeditor.views.main.modules.common.modules.ModulesView;

public class UnitRacesMetaEvent extends AbstractMetaEvent {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addEvents() {
        SetModuleNameEvent moduleNameEvent = new SetModuleNameEvent(ModulesView.class, MainView.class);
        ShowUnitsRacesViewEvent event = EventCreator.createEvent(UnitsRacesView.class, MainView.class, ShowUnitsRacesViewEvent.class);
        ClearViewEvent clearViewEvent = new ClearViewEvent<>(UnitsRacesView.class, MainView.class);
        addEvents(moduleNameEvent, event, clearViewEvent);
    }
}

class ShowUnitsRacesViewEvent extends ShowViewEvent<UnitsRacesView, MainView, Object> {

    public ShowUnitsRacesViewEvent(ClassTuple<UnitsRacesView, MainView> tuple) {
        super(tuple);
    }
}
