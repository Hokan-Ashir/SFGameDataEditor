package sfgamedataeditor.views.main.modules.buildings;

import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.ShowViewEvent;
import sfgamedataeditor.views.main.modules.ModulesView;

public class ShowBuildingRacesViewEvent extends ShowViewEvent<BuildingRacesView, ModulesView, Object> {

    public ShowBuildingRacesViewEvent(ClassTuple<BuildingRacesView, ModulesView> tuple) {
        super(tuple);
    }
}
