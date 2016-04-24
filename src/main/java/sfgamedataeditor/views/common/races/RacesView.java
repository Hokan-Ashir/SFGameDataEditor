package sfgamedataeditor.views.common.races;

import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.common.AbstractModulesView;
import sfgamedataeditor.views.common.AbstractView;
import sfgamedataeditor.views.common.notimplemented.NotImplementedMetaEvent;

public class RacesView<T extends AbstractView> extends AbstractModulesView<T> {

    public RacesView(T parentView) {
        super(parentView, I18N.INSTANCE.getMessage("races"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fillComboBoxMapping() {
        NotImplementedMetaEvent event = new NotImplementedMetaEvent();
        addMapping(I18N.INSTANCE.getMessage("race.humans"), event);
        addMapping(I18N.INSTANCE.getMessage("race.elves"), event);
        addMapping(I18N.INSTANCE.getMessage("race.dwarves"), event);
        addMapping(I18N.INSTANCE.getMessage("race.orcs"), event);
        addMapping(I18N.INSTANCE.getMessage("race.trolls"), event);
        addMapping(I18N.INSTANCE.getMessage("race.darkElves"), event);
    }

}
