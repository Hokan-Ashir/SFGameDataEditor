package sfgamedataeditor.views.main.modules.buildings;

import sfgamedataeditor.views.common.races.AbstractRacesView;
import sfgamedataeditor.views.main.MainView;

public class BuildingRacesView extends AbstractRacesView<MainView, BuildingRacesMetaEvent> {

    public BuildingRacesView(MainView parentView) {
        super(parentView);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<BuildingRacesMetaEvent> getMetaEventClass() {
        return BuildingRacesMetaEvent.class;
    }
}
