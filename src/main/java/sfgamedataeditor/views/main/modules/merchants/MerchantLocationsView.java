package sfgamedataeditor.views.main.modules.merchants;

import sfgamedataeditor.mvc.objects.AbstractController;
import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.common.AbstractModulesView;

public class MerchantLocationsView extends AbstractModulesView {

    public MerchantLocationsView() {
        super(I18N.INSTANCE.getMessage("merchantLocations"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fillComboBoxMapping() {

    }

    @Override
    public Class<? extends AbstractController> getControllerClass() {
        return MerchantLocationsController.class;
    }
}
