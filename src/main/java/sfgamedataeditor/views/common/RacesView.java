package sfgamedataeditor.views.common;

import sfgamedataeditor.utils.I18N;

public class RacesView<T extends AbstractView> extends AbstractModulesView<T> {

    public RacesView(T parentView) {
        super(parentView);
    }

    @Override
    protected void fillComboBoxMapping() {
        // TODO add messages
        getComboBoxMapping().put(I18N.INSTANCE.getMessage(""), NotImplementedView.class);
        getComboBoxMapping().put(I18N.INSTANCE.getMessage(""), NotImplementedView.class);
        getComboBoxMapping().put(I18N.INSTANCE.getMessage(""), NotImplementedView.class);
        getComboBoxMapping().put(I18N.INSTANCE.getMessage(""), NotImplementedView.class);
        getComboBoxMapping().put(I18N.INSTANCE.getMessage(""), NotImplementedView.class);
        getComboBoxMapping().put(I18N.INSTANCE.getMessage(""), NotImplementedView.class);
    }

    @Override
    public void show() {

    }
}
