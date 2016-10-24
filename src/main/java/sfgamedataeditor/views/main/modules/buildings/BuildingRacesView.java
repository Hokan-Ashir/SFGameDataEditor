package sfgamedataeditor.views.main.modules.buildings;

import sfgamedataeditor.views.common.races.AbstractRacesView;
import sfgamedataeditor.views.main.modules.common.modules.ModulesView;

public class BuildingRacesView extends AbstractRacesView<ModulesView, BuildingRacesMetaEvent> {

    public BuildingRacesView(ModulesView parentView) {
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
