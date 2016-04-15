package sfgamedataeditor.views.common;

import sfgamedataeditor.utils.I18N;

public class RacesView<T extends AbstractView> extends AbstractModulesView<T> {

    public RacesView(T parentView) {
        super(parentView);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fillComboBoxMapping() {
        getComboBoxMapping().put(I18N.INSTANCE.getMessage("race.humans"), NotImplementedView.class);
        getComboBoxMapping().put(I18N.INSTANCE.getMessage("race.elves"), NotImplementedView.class);
        getComboBoxMapping().put(I18N.INSTANCE.getMessage("race.dwarves"), NotImplementedView.class);
        getComboBoxMapping().put(I18N.INSTANCE.getMessage("race.orcs"), NotImplementedView.class);
        getComboBoxMapping().put(I18N.INSTANCE.getMessage("race.trolls"), NotImplementedView.class);
        getComboBoxMapping().put(I18N.INSTANCE.getMessage("race.darkElves"), NotImplementedView.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void show() {

    }
}
