package sfgamedataeditor.views.main.modules.buildings;

import sfgamedataeditor.events.AbstractMetaEvent;
import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.EventCreator;
import sfgamedataeditor.events.ShowViewEvent;
import sfgamedataeditor.views.main.modules.common.modules.ModulesView;

public class BuildingRacesMetaEvent extends AbstractMetaEvent {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addEvents() {
        ShowBuildingRacesViewEvent event = EventCreator.createEvent(BuildingRacesView.class, ModulesView.class, ShowBuildingRacesViewEvent.class);
        addEvent(event);
    }
}

class ShowBuildingRacesViewEvent extends ShowViewEvent<BuildingRacesView, ModulesView, Object> {

    public ShowBuildingRacesViewEvent(ClassTuple<BuildingRacesView, ModulesView> tuple) {
        super(tuple);
    }
}
