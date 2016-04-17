package sfgamedataeditor.views.main.modules.items.buildingplans;

import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.ShowViewEvent;
import sfgamedataeditor.views.main.modules.items.ItemTypesView;

public class ShowBuildingPlansViewEvent extends ShowViewEvent<BuildingPlansListView, ItemTypesView, Object> {

    public ShowBuildingPlansViewEvent(ClassTuple<BuildingPlansListView, ItemTypesView> tuple) {
        super(tuple);
    }
}
