package sfgamedataeditor.views.main.modules.items.buildingplans;

import sfgamedataeditor.events.AbstractMetaEvent;
import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.EventCreator;
import sfgamedataeditor.events.ShowViewEvent;
import sfgamedataeditor.views.main.modules.items.ItemTypesView;

public class BuildingPlansMetaEvent extends AbstractMetaEvent {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addEvents() {
        ShowBuildingPlansViewEvent event = EventCreator.createEvent(BuildingPlansListView.class, ItemTypesView.class, ShowBuildingPlansViewEvent.class);
        addEvent(event);
    }
}

class ShowBuildingPlansViewEvent extends ShowViewEvent<BuildingPlansListView, ItemTypesView, Object> {

    public ShowBuildingPlansViewEvent(ClassTuple<BuildingPlansListView, ItemTypesView> tuple) {
        super(tuple);
    }
}
