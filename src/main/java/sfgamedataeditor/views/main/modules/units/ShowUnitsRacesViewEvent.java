package sfgamedataeditor.views.main.modules.units;

import sfgamedataeditor.events.ClassTuple;
import sfgamedataeditor.events.ShowViewEvent;
import sfgamedataeditor.views.main.modules.ModulesView;

public class ShowUnitsRacesViewEvent extends ShowViewEvent<UnitsRacesView, ModulesView, Object> {

    public ShowUnitsRacesViewEvent(ClassTuple<UnitsRacesView, ModulesView> tuple) {
        super(tuple);
    }
}
