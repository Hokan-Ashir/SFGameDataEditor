package sfgamedataeditor.views.main.modules.items.buildingplans;

import sfgamedataeditor.views.common.races.AbstractRacesView;
import sfgamedataeditor.views.main.modules.items.ItemTypesView;

// TODO separate building plans from unit plans
public class BuildingPlansListView extends AbstractRacesView<ItemTypesView, BuildingPlansMetaEvent> {

    public BuildingPlansListView(ItemTypesView parentView) {
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
