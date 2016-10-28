package sfgamedataeditor.views.main.modules.units;

import sfgamedataeditor.views.common.AbstractView;
import sfgamedataeditor.views.common.races.AbstractRacesView;
import sfgamedataeditor.views.main.MainView;
import sfgamedataeditor.views.main.modules.common.modules.ModulesView;

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

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<? extends AbstractView> getParentHierarchyClass() {
        return ModulesView.class;
    }
}
