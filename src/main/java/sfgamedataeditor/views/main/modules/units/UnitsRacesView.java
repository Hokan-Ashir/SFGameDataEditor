package sfgamedataeditor.views.main.modules.units;

import sfgamedataeditor.views.common.races.AbstractRacesView;
import sfgamedataeditor.views.main.modules.common.modules.ModulesView;

public class UnitsRacesView extends AbstractRacesView<ModulesView, UnitRacesMetaEvent> {

    public UnitsRacesView(ModulesView parentView) {
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
