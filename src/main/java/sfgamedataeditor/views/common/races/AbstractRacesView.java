package sfgamedataeditor.views.common.races;

import sfgamedataeditor.mvc.objects.AbstractController;
import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.common.AbstractModulesView;
import sfgamedataeditor.views.common.notimplemented.NotImplementedView;

public abstract class AbstractRacesView extends AbstractModulesView {

    protected AbstractRacesView() {
        super(I18N.INSTANCE.getMessage("races"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fillComboBoxMapping() {
        addMapping(I18N.INSTANCE.getMessage("race.humans"), NotImplementedView.class);
        addMapping(I18N.INSTANCE.getMessage("race.elves"), NotImplementedView.class);
        addMapping(I18N.INSTANCE.getMessage("race.dwarves"), NotImplementedView.class);
        addMapping(I18N.INSTANCE.getMessage("race.orcs"), NotImplementedView.class);
        addMapping(I18N.INSTANCE.getMessage("race.trolls"), NotImplementedView.class);
        addMapping(I18N.INSTANCE.getMessage("race.darkElves"), NotImplementedView.class);
    }

    @Override
    public Class<? extends AbstractController> getControllerClass() {
        return AbstractRacesController.class;
    }
}
