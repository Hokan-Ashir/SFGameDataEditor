package sfgamedataeditor.views.main.modules.merchants;

import sfgamedataeditor.utils.I18N;
import sfgamedataeditor.views.common.AbstractModulesView;
import sfgamedataeditor.views.main.MainView;

public class MerchantLocationsView extends AbstractModulesView<MainView, MerchantLocationsMetaEvent> {

    public MerchantLocationsView(MainView parentView) {
        super(parentView, I18N.INSTANCE.getMessage("merchantLocations"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fillComboBoxMapping() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<MerchantLocationsMetaEvent> getMetaEventClass() {
        return MerchantLocationsMetaEvent.class;
    }
}
