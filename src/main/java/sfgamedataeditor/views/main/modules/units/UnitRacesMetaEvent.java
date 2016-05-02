package sfgamedataeditor.views.main.modules.units;

import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.EventCreator;
import sfgamedataeditor.events.types.AbstractMetaEvent;
import sfgamedataeditor.events.types.ClearViewEvent;
import sfgamedataeditor.events.types.ShowViewEvent;
import sfgamedataeditor.views.main.modules.common.modules.ModulesView;

public class UnitRacesMetaEvent extends AbstractMetaEvent {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addEvents() {
        ShowUnitsRacesViewEvent event = EventCreator.createEvent(UnitsRacesView.class, ModulesView.class, ShowUnitsRacesViewEvent.class);
        ClearViewEvent clearViewEvent = new ClearViewEvent(UnitsRacesView.class, ModulesView.class);
        addEvents(event, clearViewEvent);
    }
}

class ShowUnitsRacesViewEvent extends ShowViewEvent<UnitsRacesView, ModulesView, Object> {

    public ShowUnitsRacesViewEvent(ClassTuple<UnitsRacesView, ModulesView> tuple) {
        super(tuple);
    }
}
