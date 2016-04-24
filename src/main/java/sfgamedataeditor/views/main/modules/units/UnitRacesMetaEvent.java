package sfgamedataeditor.views.main.modules.units;

import sfgamedataeditor.events.AbstractMetaEvent;
import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.EventCreator;
import sfgamedataeditor.events.ShowViewEvent;
import sfgamedataeditor.views.main.modules.common.modules.ModulesView;

public class UnitRacesMetaEvent extends AbstractMetaEvent {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addEvents() {
        ShowUnitsRacesViewEvent event = EventCreator.createEvent(UnitsRacesView.class, ModulesView.class, ShowUnitsRacesViewEvent.class);
        addEvent(event);
    }
}

class ShowUnitsRacesViewEvent extends ShowViewEvent<UnitsRacesView, ModulesView, Object> {

    public ShowUnitsRacesViewEvent(ClassTuple<UnitsRacesView, ModulesView> tuple) {
        super(tuple);
    }
}
