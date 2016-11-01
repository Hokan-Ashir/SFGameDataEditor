package sfgamedataeditor.views.main.modules.items.miscellaneous;

import sfgamedataeditor.mvc.objects.AbstractController;
import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.common.AbstractModulesView;

public class MiscellaneousListView extends AbstractModulesView {

    public MiscellaneousListView() {
        super(I18N.INSTANCE.getMessage("miscellaneousTypes"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fillComboBoxMapping() {

    }

    @Override
    public Class<? extends AbstractController> getControllerClass() {
        return MiscellaneousController.class;
    }
}
