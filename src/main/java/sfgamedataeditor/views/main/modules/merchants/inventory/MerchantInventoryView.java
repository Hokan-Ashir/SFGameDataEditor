package sfgamedataeditor.views.main.modules.merchants.inventory;

import sfgamedataeditor.mvc.objects.AbstractController;
import sfgamedataeditor.views.common.AbstractModulesView;
import sfgamedataeditor.views.utility.i18n.I18NService;
import sfgamedataeditor.views.utility.i18n.I18NTypes;

public class MerchantInventoryView extends AbstractModulesView {

    public MerchantInventoryView() {
        super(I18NService.INSTANCE.getMessage(I18NTypes.COMMON, "items"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fillComboBoxMapping() {
    }

    public void clearComboBoxAndMapping() {
        getModulesComboBox().removeAllItems();
        getComboBoxMapping().clear();
    }

    @Override
    public Class<? extends AbstractController> getControllerClass() {
        return MerchantInventoryController.class;
    }
}
