package sfgamedataeditor.views.main.modules.items.runes;

import sfgamedataeditor.views.common.races.AbstractRacesView;
import sfgamedataeditor.views.main.modules.items.ItemTypesView;

public class RuneRacesListView extends AbstractRacesView<ItemTypesView, RuneRacesMetaEvent> {

    public RuneRacesListView(ItemTypesView parentView) {
        super(parentView);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<RuneRacesMetaEvent> getMetaEventClass() {
        return RuneRacesMetaEvent.class;
    }
}
