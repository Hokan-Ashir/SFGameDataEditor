package sfgamedataeditor.views.common.races;

import sfgamedataeditor.events.types.AbstractMetaEvent;
import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.common.AbstractModulesView;
import sfgamedataeditor.views.common.AbstractView;
import sfgamedataeditor.views.common.notimplemented.NotImplementedMetaEvent;

public abstract class AbstractRacesView<T extends AbstractView, E extends AbstractMetaEvent> extends AbstractModulesView<T, E> {

    public AbstractRacesView(T parentView) {
        super(parentView, I18N.INSTANCE.getMessage("races"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fillComboBoxMapping() {
        addMapping(I18N.INSTANCE.getMessage("race.humans"), new NotImplementedMetaEvent());
        addMapping(I18N.INSTANCE.getMessage("race.elves"), new NotImplementedMetaEvent());
        addMapping(I18N.INSTANCE.getMessage("race.dwarves"), new NotImplementedMetaEvent());
        addMapping(I18N.INSTANCE.getMessage("race.orcs"), new NotImplementedMetaEvent());
        addMapping(I18N.INSTANCE.getMessage("race.trolls"), new NotImplementedMetaEvent());
        addMapping(I18N.INSTANCE.getMessage("race.darkElves"), new NotImplementedMetaEvent());
    }

}
