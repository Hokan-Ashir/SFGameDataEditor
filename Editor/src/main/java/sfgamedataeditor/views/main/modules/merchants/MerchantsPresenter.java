package sfgamedataeditor.views.main.modules.merchants;

import sfgamedataeditor.database.merchants.items.MerchantInventoryItemsTableService;
import sfgamedataeditor.views.common.ModuleParameter;
import sfgamedataeditor.views.common.presenters.AbstractModulesPresenter;
import sfgamedataeditor.views.main.modules.merchants.inventory.MerchantInventoryModel;
import sfgamedataeditor.views.main.modules.merchants.inventory.MerchantInventoryModelParameter;

import javax.swing.*;
import java.util.List;

public class MerchantsPresenter extends AbstractModulesPresenter<ModuleParameter, MerchantsView, MerchantInventoryModel> {

    public MerchantsPresenter(MerchantsView view) {
        super(view);
    }

    @Override
    protected MerchantInventoryModel createModel() {
        String selectedMerchantName = getView().getSelectedModuleName();
        List<Integer> itemIds = MerchantInventoryItemsTableService.INSTANCE.getInventoryItemIdsByMerchantName(selectedMerchantName);
        Icon icon = getView().getSelectedModuleIcon();
        MerchantInventoryModelParameter parameter = new MerchantInventoryModelParameter(itemIds, null, icon);
        return new MerchantInventoryModel(parameter);
    }
}
