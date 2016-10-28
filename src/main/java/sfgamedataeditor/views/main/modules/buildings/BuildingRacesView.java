package sfgamedataeditor.views.main.modules.buildings;

import sfgamedataeditor.views.common.AbstractView;
import sfgamedataeditor.views.common.races.AbstractRacesView;
import sfgamedataeditor.views.main.MainView;
import sfgamedataeditor.views.main.modules.common.modules.ModulesView;

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

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<? extends AbstractView> getParentHierarchyClass() {
        return ModulesView.class;
    }
}
