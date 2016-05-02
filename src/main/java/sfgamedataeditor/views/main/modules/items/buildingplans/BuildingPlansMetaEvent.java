package sfgamedataeditor.views.main.modules.items.buildingplans;

import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.EventCreator;
import sfgamedataeditor.events.types.AbstractMetaEvent;
import sfgamedataeditor.events.types.ClearViewEvent;
import sfgamedataeditor.events.types.ShowViewEvent;
import sfgamedataeditor.views.main.modules.items.ItemTypesView;

public class BuildingPlansMetaEvent extends AbstractMetaEvent {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addEvents() {
        ShowBuildingPlansViewEvent event = EventCreator.createEvent(BuildingPlansListView.class, ItemTypesView.class, ShowBuildingPlansViewEvent.class);
        ClearViewEvent clearViewEvent = new ClearViewEvent(BuildingPlansListView.class, ItemTypesView.class);
        addEvents(event, clearViewEvent);
    }
}

class ShowBuildingPlansViewEvent extends ShowViewEvent<BuildingPlansListView, ItemTypesView, Object> {

    public ShowBuildingPlansViewEvent(ClassTuple<BuildingPlansListView, ItemTypesView> tuple) {
        super(tuple);
    }
}
