package sfgamedataeditor.views.main.modules.items.buildingplans;

import sfgamedataeditor.views.common.races.AbstractRacesView;
import sfgamedataeditor.views.main.MainView;

// TODO separate building plans from unit plans
public class BuildingPlansListView extends AbstractRacesView<MainView, BuildingPlansMetaEvent> {

    public BuildingPlansListView(MainView parentView) {
        super(parentView);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<BuildingPlansMetaEvent> getMetaEventClass() {
        return BuildingPlansMetaEvent.class;
    }
}
