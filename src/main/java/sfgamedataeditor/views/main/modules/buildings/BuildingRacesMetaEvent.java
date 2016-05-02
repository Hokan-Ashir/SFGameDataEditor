package sfgamedataeditor.views.main.modules.buildings;

import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.EventCreator;
import sfgamedataeditor.events.types.AbstractMetaEvent;
import sfgamedataeditor.events.types.ClearViewEvent;
import sfgamedataeditor.events.types.ShowViewEvent;
import sfgamedataeditor.views.main.modules.common.modules.ModulesView;

public class BuildingRacesMetaEvent extends AbstractMetaEvent {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addEvents() {
        ShowBuildingRacesViewEvent event = EventCreator.createEvent(BuildingRacesView.class, ModulesView.class, ShowBuildingRacesViewEvent.class);
        ClearViewEvent clearViewEvent = new ClearViewEvent(BuildingRacesView.class, ModulesView.class);
        addEvents(event, clearViewEvent);
    }
}

class ShowBuildingRacesViewEvent extends ShowViewEvent<BuildingRacesView, ModulesView, Object> {

    public ShowBuildingRacesViewEvent(ClassTuple<BuildingRacesView, ModulesView> tuple) {
        super(tuple);
    }
}
