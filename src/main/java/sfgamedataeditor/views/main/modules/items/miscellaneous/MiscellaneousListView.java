package sfgamedataeditor.views.main.modules.items.miscellaneous;

import sfgamedataeditor.mvc.objects.AbstractController;
import sfgamedataeditor.views.common.AbstractModulesView;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

public class MiscellaneousListView extends AbstractModulesView {

    public MiscellaneousListView() {
        super(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "miscellaneousTypes"));
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
