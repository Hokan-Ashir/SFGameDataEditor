package sfgamedataeditor.views.main.modules.items.runes;

import sfgamedataeditor.views.common.races.AbstractRacesView;
import sfgamedataeditor.views.main.MainView;

public class RuneRacesListView extends AbstractRacesView<MainView, RuneRacesMetaEvent> {

    public RuneRacesListView(MainView parentView) {
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
