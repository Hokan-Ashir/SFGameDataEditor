package sfgamedataeditor.views.main.modules.merchants;

import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.common.AbstractModulesView;
import sfgamedataeditor.views.main.modules.ModulesView;

public class MerchantLocationsView extends AbstractModulesView<ModulesView> {

    public MerchantLocationsView(ModulesView parentView) {
        super(parentView, I18N.INSTANCE.getMessage("merchantLocations"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fillComboBoxMapping() {

    }

}
