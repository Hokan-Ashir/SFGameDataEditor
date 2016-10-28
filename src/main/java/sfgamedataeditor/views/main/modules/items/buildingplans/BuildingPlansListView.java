package sfgamedataeditor.views.main.modules.items.buildingplans;

import sfgamedataeditor.views.common.AbstractView;
import sfgamedataeditor.views.common.races.AbstractRacesView;
import sfgamedataeditor.views.main.MainView;
import sfgamedataeditor.views.main.modules.common.modules.ModulesView;

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

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<? extends AbstractView> getParentHierarchyClass() {
        return ModulesView.class;
    }
}
