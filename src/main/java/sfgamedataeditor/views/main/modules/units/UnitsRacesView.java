package sfgamedataeditor.views.main.modules.units;

import sfgamedataeditor.views.common.races.AbstractRacesView;
import sfgamedataeditor.views.main.MainView;

public class UnitsRacesView extends AbstractRacesView<MainView, UnitRacesMetaEvent> {

    public UnitsRacesView(MainView parentView) {
        super(parentView);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<UnitRacesMetaEvent> getMetaEventClass() {
        return UnitRacesMetaEvent.class;
    }
}
