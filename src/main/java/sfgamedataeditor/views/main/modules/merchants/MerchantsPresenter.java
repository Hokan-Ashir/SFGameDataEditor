package sfgamedataeditor.views.main.modules.merchants;

import sfgamedataeditor.database.merchants.items.MerchantInventoryItemsTableService;
import sfgamedataeditor.views.common.AbstractModulesPresenter;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.main.modules.merchants.inventory.MerchantInventoryModel;
import sfgamedataeditor.views.main.modules.merchants.inventory.MerchantInventoryModelParameter;

import java.util.List;

public class MerchantsPresenter extends AbstractModulesPresenter<ModuleParameter, MerchantsView, MerchantInventoryModel> {

    public MerchantsPresenter(MerchantsView view) {
        super(view);
    }

    @Override
    protected MerchantInventoryModel createModel() {
        String selectedMerchantName = getView().getSelectedModuleValue();
        List<Integer> itemIds = MerchantInventoryItemsTableService.INSTANCE.getInventoryItemIdsByMerchantName(selectedMerchantName);
        MerchantInventoryModelParameter parameter = new MerchantInventoryModelParameter(itemIds, null);
        return new MerchantInventoryModel(parameter);
    }

    @Override
    public void updateView() {
        if (getModel() == null) {
            setModulesComboBoxValue(null);
            return;
        }

        String moduleName = getModel().getParameter().getModuleName();
        if (isElementExistsInComboBox(moduleName)) {
            setModulesComboBoxValue(moduleName);
        } else {
            setModulesComboBoxValue(null);
        }
    }
}
